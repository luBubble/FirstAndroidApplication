package com.zl.tesseract.scanner.myPaper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.text.SimpleDateFormat;

public class MyPaperFragment extends Fragment  implements AdapterView.OnItemClickListener {
private ListView list_paper;
private Handler handler ;
private PaperAdapter paperAdapter;
private JSONArray paperJSONArray;
private int teacherId;
private ImageView nothing_image;
private TextView nothing_text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        teacherId=getArguments().getInt("teacherId");
        View view=inflater.inflate(R.layout.search_and_list,container,false);
        handler=new Handler();
        list_paper=(ListView)view.findViewById(R.id.list);
        list_paper.setOnItemClickListener(this);
        nothing_image=(ImageView)view.findViewById(R.id.msg_image);
        nothing_text=(TextView)view.findViewById(R.id.msg_text);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("MyPaperFragment","运行onResume");
        GetAllExamInformation();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent studentDetail = new Intent(getActivity(), MyPaperGradeActivity.class);
        Bundle bd=new Bundle();
        try {
            bd.putString("paperId","000"+paperJSONArray.getJSONObject(i).getString("exam_id"));
            Date date =new Date(paperJSONArray.getJSONObject(i).getLong("exam_time"));//转成时间格式
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//设置转化格式
            String time=sdf.format(date);
            bd.putString("paperBuildTime",time);
            bd.putString("paperName",paperJSONArray.getJSONObject(i).getString("exam_name"));
            bd.putInt("teacherId",teacherId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        studentDetail.putExtras(bd);//传值给activity
        startActivity(studentDetail);//打开详情页
    }
    //获取试卷数据
    public void GetAllExamInformation() {
        //list.removeAll(list);
        new Thread() {
            public void run() {
                String getExamsUrl=HttpUtil.path+"homework/Exam/allExamsOfTeacher/"+teacherId+".do";
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String str = HttpUtil.doPost(getExamsUrl, map);
                    if(str!=null&&!str.equals("[]"))
                    {
                        paperJSONArray=new JSONArray(str);
                        //填充清单分类数据
                        if(paperAdapter==null) {
                            paperAdapter = new PaperAdapter(paperJSONArray, getActivity());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    nothing_image.setVisibility(View.GONE);
                                    nothing_text.setVisibility(View.GONE);
                                    list_paper.setAdapter(paperAdapter);//首次读取
                                }
                            });
                        }
                        else
                        {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    nothing_image.setVisibility(View.GONE);
                                    nothing_text.setVisibility(View.GONE);
                                    paperAdapter.reSet(paperJSONArray);//刷新数据
                                }
                            });
                        }
                    }
                    else
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                nothing_image.setVisibility(View.VISIBLE);
                                nothing_text.setText("暂无试卷，快去添加试卷吧~");
                                nothing_text.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("MyPaperFragment","发生异常");
                    e.printStackTrace();
                }}
        }.start();
    }


}
