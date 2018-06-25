package com.zl.tesseract.scanner.more;

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
import com.zl.tesseract.scanner.bean.Student;
import com.zl.tesseract.scanner.mainView.MyStudentDetailActivity;

import java.util.LinkedList;
import java.util.List;

public class MyAddApplyFragement extends Fragment implements AdapterView.OnItemClickListener{
    private List<Student> mData;
    private FragmentManager fManager;
    private ListView myApplyList;
    private ApplyStudentAdapter mAdapter;

    @SuppressLint("ValidFragment")
    public MyAddApplyFragement(FragmentManager fManager) {
        this.fManager = fManager;
    }
    public MyAddApplyFragement() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_apply,container,false);
        myApplyList=(ListView)view.findViewById(R.id.add_apply_list);
        myApplyList.setOnItemClickListener(this);
        mData=new LinkedList<Student>();
        mData.add(new Student("邓萍萍","20180330422","123456","中南光明小学","三年二班","9","13277354776","女"));
        mData.add(new Student("白敬亭","20180426171211","123456","中南光明小学","三年二班","9","13267089663","男"));
        mData.add(new Student("李大明","20180428103426","123456","中南光明小学","三年二班","9","13267089625","男"));
        mData.add(new Student("王红红","20180429215608","123456","中南光明小学","三年二班","9","13456579781","女"));
        mData.add(new Student("刘小强","20180501134622","123456","中南光明小学","二年三班","9","132735685744","男"));
        mAdapter=new ApplyStudentAdapter((LinkedList<Student>)mData,getActivity());
        myApplyList.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bd = new Bundle();
        bd.putString("type","apply");
        bd.putString("name", mData.get(i).getName());
        bd.putString("id", mData.get(i).getId());
        bd.putString("school", mData.get(i).getSchool());
        bd.putString("studentClass", mData.get(i).getStudentClass());
        bd.putString("age", mData.get(i).getAge());
        bd.putString("sex", mData.get(i).getSex());
        bd.putString("phone", mData.get(i).getPhone());
        bd.putInt("pic",i);
        Intent studentDetail = new Intent(getActivity(), MyStudentDetailActivity.class);
        studentDetail.putExtras(bd);//传值给activity
        startActivity(studentDetail);//打开详情页
    }


}
