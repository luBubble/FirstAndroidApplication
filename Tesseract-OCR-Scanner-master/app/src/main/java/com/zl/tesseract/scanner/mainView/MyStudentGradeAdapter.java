package com.zl.tesseract.scanner.mainView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;

import java.util.List;
import java.util.Map;

public class MyStudentGradeAdapter extends BaseAdapter {
    private List<Map<String,String>> mData;
    private Context mContext;

    public MyStudentGradeAdapter(List<Map<String, String>> mData, Context mContext) {
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
        view= LayoutInflater.from(mContext).inflate(R.layout.my_student_grade_list,null);
        TextView paperName=(TextView) view.findViewById(R.id.student_grade_paper_name);
        TextView scanTime=(TextView) view.findViewById(R.id.student_grade_scan_time);
        TextView score=(TextView) view.findViewById(R.id.student_grade);
        paperName.setText(mData.get(i).get("paper_name"));
        scanTime.setText(mData.get(i).get("scan_Time"));
        score.setText(mData.get(i).get("score"));
        return view;
    }
}
