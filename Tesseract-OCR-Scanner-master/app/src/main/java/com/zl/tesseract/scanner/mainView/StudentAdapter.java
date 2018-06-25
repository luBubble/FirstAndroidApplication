package com.zl.tesseract.scanner.mainView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;
import com.zl.tesseract.scanner.more.RoundImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private JSONArray mData;
    private Context mContext;

    public StudentAdapter(JSONArray mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return mData.getJSONObject(i);
        } catch (JSONException e) {
            return e;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mContext).inflate(R.layout.my_student_list,null);
        TextView studentName=(TextView) view.findViewById(R.id.student_name);
        TextView studentClass=(TextView) view.findViewById(R.id.student_class);
        try {
            studentName.setText(mData.getJSONObject(i).getString("student_name"));
            studentClass.setText(mData.getJSONObject(i).getString("student_class")+"班");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void reSet(JSONArray datas) {
        mData = null;
        mData = new JSONArray();
        mData=datas;
        notifyDataSetChanged();
    }
}
