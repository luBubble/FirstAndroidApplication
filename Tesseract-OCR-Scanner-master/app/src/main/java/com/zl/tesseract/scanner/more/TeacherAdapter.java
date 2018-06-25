package com.zl.tesseract.scanner.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;
import com.zl.tesseract.scanner.bean.Teacher;

public class TeacherAdapter {
    private Teacher mData;
    private Context mContext;

    public TeacherAdapter(Teacher mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        view= LayoutInflater.from(mContext).inflate(R.layout.teacher_message,null);
        TextView teacher_name=(TextView) view.findViewById(R.id.user_msg_name);
        TextView teacher_id=(TextView) view.findViewById(R.id.user_msg_id);
        TextView teacher_des=(TextView)view.findViewById(R.id.user_msg_description);
        TextView teacher_tel=(TextView)view.findViewById(R.id.user_msg_phone);
        teacher_name.setText(mData.getName());
        teacher_des.setText(mData.getDescription());
        teacher_tel.setText(mData.getPhone());
        teacher_id.setText(mData.getId());
        return view;
    }
}

