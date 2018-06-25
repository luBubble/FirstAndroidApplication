package com.zl.tesseract.scanner.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;

import java.util.List;

public class StudentResultAdapter extends BaseAdapter {
    private List<Student> mData;
    private Context mContext;

    public StudentResultAdapter(List<Student> mData, Context mContext) {
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
        view= LayoutInflater.from(mContext).inflate(R.layout.my_student_list,null);
        TextView studentName=(TextView) view.findViewById(R.id.student_name);
        TextView studentSchool=(TextView) view.findViewById(R.id.student_class);
        studentName.setText(mData.get(i).getName());
        studentSchool.setText(mData.get(i).getSchool());
        return view;
    }
}
