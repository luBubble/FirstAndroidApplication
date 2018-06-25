package com.zl.tesseract.scanner.student.myTeacherAndParent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Teacher;
import com.zl.tesseract.scanner.more.RoundImageView;

import java.util.List;

public class TPAdapter extends BaseAdapter {
    private List<Teacher> mData;
    private Context mContext;

    public TPAdapter(List<Teacher> mData, Context mContext) {
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
        view= LayoutInflater.from(mContext).inflate(R.layout.search_student_list,null);
        RoundImageView pic=(RoundImageView)view.findViewById(R.id.student_pic) ;
        TextView teacherName=(TextView) view.findViewById(R.id.student_name);
        TextView teacherSchool=(TextView) view.findViewById(R.id.student_school);
        teacherName.setText(mData.get(i).getName());
        teacherSchool.setText(mData.get(i).getSchool());
        pic.setImageResource(R.mipmap.t0);
        return null;
    }
}
