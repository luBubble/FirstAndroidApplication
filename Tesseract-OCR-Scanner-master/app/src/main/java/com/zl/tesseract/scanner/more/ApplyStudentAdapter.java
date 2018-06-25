package com.zl.tesseract.scanner.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;

import java.util.List;

public class ApplyStudentAdapter extends BaseAdapter {
    private List<Student> mData;
    private Context mContext;

    public ApplyStudentAdapter(List<Student> mData, Context mContext) {
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
        int image[] = new int[]{
                R.mipmap.s0,
                R.mipmap.s1,
                R.mipmap.s2,
                R.mipmap.s3,
                R.mipmap.s4,
        };
        view= LayoutInflater.from(mContext).inflate(R.layout.add_apply_list,null);
        TextView studentName=(TextView) view.findViewById(R.id.student_name);
        TextView state=(TextView)view.findViewById(R.id.text_state) ;
        RoundImageView student_pic=(RoundImageView)view.findViewById(R.id.student_pic);

        studentName.setText(mData.get(i).getName());
        if(mData.get(i).getName().equals("鹿小葵"))
        {
            state.setText("已同意");
            student_pic.setImageResource(R.mipmap.img);
        }
        else if(mData.get(i).getName().equals("邓萍萍"))
        {
            state.setText("未处理");
            student_pic.setImageResource(R.mipmap.img);

        }else
        {
            student_pic.setImageResource(image[i]);
            state.setText("已同意");
        }
        return view;
    }
}
