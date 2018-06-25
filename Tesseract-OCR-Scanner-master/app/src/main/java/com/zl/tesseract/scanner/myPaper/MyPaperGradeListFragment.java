package com.zl.tesseract.scanner.myPaper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
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
import com.zl.tesseract.scanner.bean.Student;
import com.zl.tesseract.scanner.mainView.MyStudentGradeDetailFragment;
import com.zl.tesseract.scanner.mainView.StudentAdapter;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyPaperGradeListFragment extends Fragment implements AdapterView.OnItemClickListener{
    private FragmentManager fManager;
    private ListView paper_grade_list;
    private List<Grade> mData;
    private MyPaperGradeAdapter mAdapter;

    @SuppressLint("ValidFragment")
    public MyPaperGradeListFragment(FragmentManager fManager) {
        this.fManager = fManager;
    }
    public MyPaperGradeListFragment(){}
    private int teacherId;
    String paperId;
    String paperName;
    String paperBuildTime;
    String teacherName;
    private ImageView nothing_image;
    private TextView nothing_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_and_list,container,false);
        paper_grade_list=(ListView)view.findViewById(R.id.list);
        paper_grade_list.setOnItemClickListener(this);
        nothing_image=(ImageView)view.findViewById(R.id.msg_image);
        nothing_text=(TextView)view.findViewById(R.id.msg_text);
        paperId=getArguments().getString("paperId");
        paperName=getArguments().getString("paperName");
        paperBuildTime=getArguments().getString("paperBuildTime");
        teacherId=getArguments().getInt("teacherId");
        //获取老师名字
        new Thread(){
            public void run()
            {
                String url_t=HttpUtil.path+"homework/Student/oneTeacher/"+teacherId+".do";
                Map<String, Object> map = new HashMap<String, Object>();
                String str_t = HttpUtil.doPost(url_t, map);
                Log.e("获取老师名字str_t",str_t);
                JSONObject jsonObject_t= null;
                try {
                    jsonObject_t = new JSONObject(str_t);
                    teacherName=jsonObject_t.getString("teacher_name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        GetOneExamAllGrade();//获取所有成绩
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        MyStudentGradeDetailFragment studentGradeDetail = new MyStudentGradeDetailFragment(fManager);
        Bundle bd=new Bundle();
        bd.putString("studentName",mData.get(i).getStudentName());
        bd.putString("teacherName",mData.get(i).getTeacherName());
        bd.putString("paper_name", mData.get(i).getPaperName());
        bd.putString("scan_Time", mData.get(i).getScanTime());
        bd.putString("score", mData.get(i).getScore());
        bd.putString("wrong", mData.get(i).getWrong());
        studentGradeDetail.setArguments(bd);//把当前数据传过去
        //获取Activity的控件
        TextView txt_toolbar = (TextView) getActivity().findViewById(R.id.txt_toolbar);
        txt_toolbar.setText("成绩详情");
        fTransaction.replace(R.id.no_menu_framelayout, studentGradeDetail);
        //调用addToBackStack将Fragment添加到栈中
        fTransaction.addToBackStack(null);
        fTransaction.commit();
    }
    public void GetOneExamAllGrade() {
        //list.removeAll(list);
        new Thread() {
            public void run() {
                try {
                    mData=new LinkedList<Grade>();
                    Map<String, Object> map = new HashMap<String, Object>();
                    // map.put("type", "学生");
                    String url=HttpUtil.path+"homework/Exam/Grade/"+paperId+".do";
                    String str = HttpUtil.doPost(url, map);
                    Log.e("xianshi",str);
                    if(str!=null&&!str.equals("[]")&&!"".equals(str))
                    {
                    JSONArray jsonArray = new JSONArray(str);
                    Log.e("试卷成绩列表",String.valueOf(jsonArray.length()));
                    for (int i = 0; i <jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Grade ms = new Grade();
                        String S_id= String.valueOf(jsonObject.getInt("grade_studentId"));
                        //获取学生名字
                        String url_s=HttpUtil.path+"homework/Student/oneStudent/"+S_id+".do";
                        String str_s = HttpUtil.doPost(url_s, map);
                        JSONObject jsonObject_s=new JSONObject(str_s);
                        ms.setStudentName(jsonObject_s.getString("student_name"));
                        ms.setTeacherName(teacherName);
                        ms.setPaperName(paperName);
                        ms.setScore(jsonObject.getString("grade_num"));
                        ms.setScanTime(paperBuildTime);;
                        if(jsonObject.getString("grade_wrong").isEmpty())
                        {
                            ms.setWrong("无");
                        }
                        else
                        {
                            ms.setWrong("第"+jsonObject.getString("grade_wrong")+"题");//由于json的age为整形暂时未改
                        }

                        mData.add(ms);
                        Log.e("xianshi",jsonArray.getJSONObject(i).toString());
                    }
                    //绑定数据与ListView
                    handler.sendEmptyMessage(0x222);
                    }
                    else
                    {
                        handler.sendEmptyMessage(0x223);//无数据提示
                    }
                } catch (Exception e) {
                    Log.e("xianshi","发生异常");
                    e.printStackTrace();
                }}
        }.start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x222) {
                nothing_image.setVisibility(View.GONE);
                nothing_text.setVisibility(View.GONE);
                mAdapter=new MyPaperGradeAdapter(mData,getActivity());
                paper_grade_list.setAdapter(mAdapter);
            }
            if (msg.what == 0x223) {
                nothing_image.setVisibility(View.VISIBLE);
                nothing_text.setText("无成绩数据，去扫描添加成绩吧~");
                nothing_text.setVisibility(View.VISIBLE);
            }

        };
    };

}
