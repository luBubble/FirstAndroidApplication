package com.zl.tesseract.scanner.mainView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.JSONArray;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;
import com.loopj.android.http.*;
import com.zl.tesseract.scanner.saveGrade.PickStudentActivity;
import com.zl.tesseract.scanner.tool.HttpUtil;

import android.os.Handler;
import android.widget.Toast;

import org.apache.http.*;

import java.util.LinkedList;

public class MyStudentFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView list_student;
    private TextView txt_search;
    private Handler handler;
    private StudentAdapter studentAdapter;
    private JSONArray studentJSONArray;
    private int teacherId;
    private ImageView nothing_image;
    private TextView nothing_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        teacherId=getArguments().getInt("teacherId");
        View view=inflater.inflate(R.layout.search_and_list,container,false);
        list_student=(ListView)view.findViewById(R.id.list);
        list_student.setOnItemClickListener(this);//不能丢
        txt_search=(TextView)view.findViewById(R.id.txt_search) ;
        nothing_image=(ImageView)view.findViewById(R.id.msg_image);
        nothing_text=(TextView)view.findViewById(R.id.msg_text);
        handler=new Handler();
        //点击刷新
        txt_search.setOnClickListener(//点击搜索
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GetAllStudentInformation();
                    }
                }
        );
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetAllStudentInformation();
        Log.e("MyStudentFragment","这里onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("MyStudentFragment","这里onStart");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bd = new Bundle();
        try {
            bd.putString("num",studentJSONArray.getJSONObject(i).getString("student_account"));//学号
            bd.putString("name", studentJSONArray.getJSONObject(i).getString("student_name"));
            bd.putString("id", studentJSONArray.getJSONObject(i).getString("student_id"));
            bd.putString("school",studentJSONArray.getJSONObject(i).getString("student_schname"));
            bd.putString("studentClass",studentJSONArray.getJSONObject(i).getString("student_class"));
            bd.putString("age", studentJSONArray.getJSONObject(i).getString("student_age"));
            bd.putString("sex", studentJSONArray.getJSONObject(i).getString("student_sex"));
            bd.putString("phone",studentJSONArray.getJSONObject(i).getString("student_tel"));
            bd.putInt("pic",i);
            bd.putString("type","myStudent");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent studentDetail = new Intent(getActivity(), MyStudentDetailActivity.class);
        studentDetail.putExtras(bd);//传值给activity
        startActivity(studentDetail);//打开详情页
    }

    //获取数据
    public void GetAllStudentInformation() {
        //list.removeAll(list);
        new Thread() {
            public void run() {
                String getStudentsUrl=HttpUtil.path+"homework/Student/allStudentsOfTeacher/"+teacherId+".do";
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String str = HttpUtil.doPost(getStudentsUrl, map);
                    Log.d("MyStudent_reGetData",str);
                    if(str!=null&&!str.equals("[]"))
                    {
                        studentJSONArray=new JSONArray(str);
                        //填充清单分类数据
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                nothing_image.setVisibility(View.GONE);
                                nothing_text.setVisibility(View.GONE);
                                if(studentAdapter==null)
                                {
                                    studentAdapter=new StudentAdapter(studentJSONArray,getActivity());
                                    list_student.setAdapter(studentAdapter);
                                }
                                else
                                {
                                    studentAdapter.reSet(studentJSONArray);//刷新数据
                                }
                            }
                        });
                    }
                    else
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                nothing_image.setVisibility(View.VISIBLE);
                                nothing_text.setText("无学生数据，快去添加学生吧~");
                                nothing_text.setVisibility(View.VISIBLE);
                            }
                        });
//                        Looper.prepare();
//                        Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
//                        Looper.loop();//解决线程
                    }
                } catch (Exception e) {
                    Log.e("PickStudentActivity","发生异常");
                    e.printStackTrace();
                }}
        }.start();
    }


}
