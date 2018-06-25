package com.zl.tesseract.scanner.student.myGrade;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.mainView.MyStudentGradeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGradeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FragmentManager fManager;
    private ListView grade_list;
    private List<Map<String,String>> mData;
    private MyStudentGradeAdapter mAdapter;

    @SuppressLint("ValidFragment")
    public MyGradeFragment(FragmentManager fManager) {
        this.fManager = fManager;
    }
    public MyGradeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_and_list,container,false);
        grade_list=(ListView)view.findViewById(R.id.list);
        grade_list.setOnItemClickListener(this);
//假数据
        mData=new ArrayList<Map<String, String>>();
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("paper_name", "考前自测");
        map1.put("scan_Time", "2018-05-02");
        map1.put("score", "100");
        map1.put("wrong","无");
        Map<String,String> map2= new HashMap<String, String>();
        map2.put("paper_name", "期末考试模拟考（第一卷）");
        map2.put("scan_Time", "2018-05-02");
        map2.put("score", "80");
        map2.put("wrong","第3题");
        Map<String,String> map3 = new HashMap<String, String>();
        map3.put("paper_name", "课堂算数测试");
        map3.put("scan_Time", "2018-05-03");
        map3.put("score", "60");
        map3.put("wrong","第1,，2题");
        Map<String,String> map4= new HashMap<String, String>();
        map4.put("paper_name", "期末考试模拟考（第二卷）");
        map4.put("scan_Time", "2018-05-03");
        map4.put("score", "80");
        map4.put("wrong","第5题");
        mData.add(map1);
        mData.add(map2);
        mData.add(map3);
        mData.add(map4);
        mAdapter=new MyStudentGradeAdapter(mData,getActivity());
        grade_list.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent =new Intent(getActivity(), MyGradeDetailActivity.class);
        Bundle bd=new Bundle();
        bd.putString("studentName","罗玲玲");
        bd.putString("paper_name", mData.get(i).get("paper_name"));
        bd.putString("scan_Time", mData.get(i).get("scan_Time"));
        bd.putString("score", mData.get(i).get("score"));
        bd.putString("wrong", mData.get(i).get("wrong"));
        intent.putExtras(bd);//传数据
        startActivity(intent);
    }
}

