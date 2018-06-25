package com.zl.tesseract.scanner.myPaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Grade;

import java.util.List;

public class MyPaperGradeAdapter extends BaseAdapter {
    private List<Grade> mData;
    private Context mContext;

    public MyPaperGradeAdapter(List<Grade> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mContext).inflate(R.layout.paper_grade_list,null);
        TextView paper_student_name=(TextView)view.findViewById(R.id.paper_student_name);
        TextView paper_wrong=(TextView)view.findViewById(R.id.paper_wrong);
        TextView student_grade=(TextView)view.findViewById(R.id.student_grade);
        paper_student_name.setText(mData.get(i).getStudentName());
        paper_wrong.setText(mData.get(i).getWrong());
        student_grade.setText(mData.get(i).getScore());
        return view;
    }

}
