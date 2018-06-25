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

import java.util.LinkedList;
import java.util.List;

public class StudentApplyFragment extends Fragment  implements AdapterView.OnItemClickListener{
    private List<Student> mData;
    private FragmentManager fManager;
    private ListView myApplyList;
    private ApplyStudentAdapter mAdapter;

    @SuppressLint("ValidFragment")
    public StudentApplyFragment(FragmentManager fManager) {
        this.fManager = fManager;
    }
    public StudentApplyFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_apply,container,false);
        myApplyList=(ListView)view.findViewById(R.id.add_apply_list);
        myApplyList.setOnItemClickListener(this);
        mData=new LinkedList<Student>();
        mData.add(new Student("鹿小葵","20180503113045","123456","中南光明小学","三年三班","9","13273568962","女"));
        mData.add(new Student("罗玲玲","20180426170402","123456","中南光明小学","三年二班","9","13267085628","女"));
        mAdapter=new ApplyStudentAdapter((LinkedList<Student>)mData,getActivity());
        myApplyList.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bd = new Bundle();
        bd.putString("name", mData.get(i).getName());
        bd.putString("id", mData.get(i).getId());
        bd.putString("school", mData.get(i).getSchool());
        bd.putString("studentClass", mData.get(i).getStudentClass());
        bd.putString("age", mData.get(i).getAge());
        bd.putString("sex", mData.get(i).getSex());
        bd.putString("phone", mData.get(i).getPhone());
        Intent applyDetail = new Intent(getParentFragment().getActivity(), StudentApplyDetailActivity .class);
        applyDetail.putExtras(bd);//传值给activity
        startActivity(applyDetail);//打开详情页
    }

}
