package com.zl.tesseract.scanner.mainView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.tesseract.R;

public class MyStudentGradeDetailFragment extends Fragment {
    private FragmentManager fManager;

    @SuppressLint("ValidFragment")
    public MyStudentGradeDetailFragment(FragmentManager fManager) {
        this.fManager = fManager;
    }
    public  MyStudentGradeDetailFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_student_grade_detail, container, false);
        TextView studentName=(TextView)view.findViewById(R.id.grade_detail_student_name);
        TextView paperName=(TextView)view.findViewById(R.id.grade_detail_paper_name);
        TextView teacherName=(TextView)view.findViewById(R.id.grade_detail_teache_name);
        TextView scanTime=(TextView)view.findViewById(R.id.grade_detail_scan_time);
        TextView wrongProblem=(TextView)view.findViewById(R.id.grade_detail_wrong);
        TextView score=(TextView)view.findViewById(R.id.grade_detail_score);
        studentName.setText(getArguments().getString("studentName"));
        paperName.setText(getArguments().getString("paper_name"));
        //更新
        teacherName.setText(getArguments().getString("teacherName"));
        scanTime.setText(getArguments().getString("scan_Time"));
        wrongProblem.setText(getArguments().getString("wrong"));
        score.setText(getArguments().getString("score"));
        return view;
    }
}
