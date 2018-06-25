package com.zl.tesseract.scanner.mainView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Grade;
import com.zl.tesseract.scanner.myPaper.MyPaperGradeAdapter;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyStudentGradeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FragmentManager fManager;
    private ListView grade_list;
    private List<Map<String,String>> mData;
    private  MyStudentGradeAdapter mAdapter;
//    private LinkedList<Grade> mData;
//    private MyPaperGradeAdapter mAdapter;
    private ImageView nothing_image;
    private TextView nothing_text;

    @SuppressLint("ValidFragment")
    public MyStudentGradeFragment(FragmentManager fManager) {
        this.fManager = fManager;
    }
    public MyStudentGradeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_and_list,container,false);
        grade_list=(ListView)view.findViewById(R.id.list);
        grade_list.setOnItemClickListener(this);
        nothing_image=(ImageView)view.findViewById(R.id.msg_image);
        nothing_text=(TextView)view.findViewById(R.id.msg_text);
        Log.e("xianshi1",getArguments().toString());
        GetOneStudentAllExam();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        MyStudentGradeDetailFragment studentGradeDetail = new MyStudentGradeDetailFragment();
        Bundle bd=new Bundle();
        bd.putString("studentName",getArguments().getString("name"));
        bd.putString("teacherName",mData.get(i).get("teacher_name"));
        bd.putString("paper_name", mData.get(i).get("paper_name"));
        bd.putString("scan_Time", mData.get(i).get("scan_Time"));
        bd.putString("score", mData.get(i).get("score"));
        bd.putString("wrong", mData.get(i).get("wrong"));
        studentGradeDetail.setArguments(bd);//把当前数据传过去
        //获取Activity的控件
        TextView txt_toolbar = (TextView) getActivity().findViewById(R.id.txt_toolbar);
        txt_toolbar.setText("成绩详情");
        fTransaction.replace(R.id.no_menu_framelayout, studentGradeDetail);
        //调用addToBackStack将Fragment添加到栈中
        fTransaction.addToBackStack(null);
        fTransaction.commit();
    }

    public void GetOneStudentAllExam() {
        //list.removeAll(list);
        new Thread() {
            public void run() {
                try {
                    mData=new LinkedList<Map<String, String>>();
                    Map<String, Object> map = new HashMap<String, Object>();
                    // map.put("type", "学生");
                    String url=HttpUtil.path+"homework/Student/StudentGrade/"+getArguments().getString("id")+".do";
                    //根据id查找学生成绩
                    String str = HttpUtil.doPost(url, map);
                    if(str!=null&&!str.equals("[]")&&!"".equals(str))
                    {
                        JSONArray jsonArray = new JSONArray(str);
                        Log.e("xianshi1",String.valueOf(jsonArray.length()));
                        for (int i = 0; i <jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            //int s_id=jsonObject.getInt("grade_studentId");
                            int e_id=jsonObject.getInt("grade_examId");
                            int t_id=jsonObject.getInt("grade_teacherId");
                            Map<String,String> ms= new HashMap<String, String>();
                            ms.put("score", jsonObject.getString("grade_num"));
                            if(jsonObject.getString("grade_wrong").isEmpty())
                            {
                                ms.put("wrong","无");
                            }
                            else
                            {
                                ms.put("wrong",jsonObject.getString("grade_wrong")+"题");
                            }
//                            String E_id= String.valueOf(jsonObject.getInt("grade_studentId"));
                            Log.e("试卷id",String.valueOf(e_id));
                            //获得试卷的信息
                            String url_e=HttpUtil.path+"homework/Exam/oneExamOfTeacher/"+e_id+".do";
                            String str_e = HttpUtil.doPost(url_e, map);
                            Log.e("获取试卷信息",str_e);
                            JSONObject jsonObject_e=new JSONObject(str_e);
                            ms.put("paper_name", jsonObject_e.getString("exam_name"));
                            Date date =new Date(jsonObject_e.getLong("exam_time"));//
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//设置转化格式
                            String time=sdf.format(date);
                            ms.put("scan_Time",time);
                            //获取老师名字
                            String url_t=HttpUtil.path+"homework/Student/oneTeacher/"+t_id+".do";
                            String str_t = HttpUtil.doPost(url_t, map);
                            JSONObject jsonObject_t=new JSONObject(str_t);
                            ms.put("teacher_name",jsonObject_t.getString("teacher_name"));
                            mData.add(ms);
                            //Log.e("xianshi",jsonArray.getJSONObject(i).toString());
                        }
                        //绑定数据与ListView
                        handler.sendEmptyMessage(0x222);
                    }
                    else
                    {
                        handler.sendEmptyMessage(0x223);
                    }

                } catch (Exception e) {
                    Log.e("xianshi1","发生异常");
                    e.printStackTrace();
                }}
        }.start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x222) {
                nothing_image.setVisibility(View.GONE);
                nothing_text.setVisibility(View.GONE);
                mAdapter=new MyStudentGradeAdapter(mData,getActivity());
                grade_list.setAdapter(mAdapter);
            }
            if(msg.what == 0x223)
            {
                nothing_image.setVisibility(View.VISIBLE);
                nothing_text.setText("无成绩数据，去扫描添加成绩吧~");
                nothing_text.setVisibility(View.VISIBLE);
            }
        };
    };
}
