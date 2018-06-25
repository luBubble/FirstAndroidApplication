package com.zl.tesseract.scanner.mainView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;

import java.util.List;

public class StudentGradeAdapter extends BaseAdapter {
    private List<Student> mData;
    private Context mContext;

    public StudentGradeAdapter(List<Student> mData, Context mContext) {
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
        view= LayoutInflater.from(mContext).inflate(R.layout.student_grade_viewpage_list,null);
        TextView studentName=(TextView) view.findViewById(R.id.student_name);
        TextView examName=(TextView)view.findViewById(R.id.exam_name);
        TextView operateMan=(TextView)view.findViewById(R.id.operate_man);
        TextView grade=(TextView)view.findViewById(R.id.grade);
        studentName.setText(mData.get(i).getName());
        examName.setText(mData.get(i).getExamName());
        operateMan.setText(mData.get(i).getOperateMan());
        grade.setText(mData.get(i).getGrade());
        return view;
    }

}
