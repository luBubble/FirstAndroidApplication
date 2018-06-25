package com.zl.tesseract.scanner.mainView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;

import java.util.LinkedList;
import java.util.List;
//所有学生成绩
public class StudentGradeFragmentPageFragment extends Fragment {
    private ListView list_student;
    private List<Student> mData;
    private StudentGradeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.student_grade_viewpage,container,false);
        list_student=(ListView)view.findViewById(R.id.list_student_grade);
        //学生假数据
        mData=new LinkedList<Student>();
        mData.add(new Student("璐璐", "数学小测试","陈老师", "80"));
        mData.add(new Student("苇苇","英语选择题","Mrs Song","90"));
        //绑定数据与ListView
        mAdapter=new StudentGradeAdapter((LinkedList<Student>)mData,getActivity());
        list_student.setAdapter(mAdapter);
        return view;
    }
}
