package com.zl.tesseract.scanner.myPaper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Paper;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaperAdapter extends BaseAdapter {
    private JSONArray mData;
    private Context mContext;

    public PaperAdapter(JSONArray mData, Context mContext) {
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
        view= LayoutInflater.from(mContext).inflate(R.layout.my_paper_list,null);
        TextView paperId=(TextView) view.findViewById(R.id.my_paper_id);
        TextView paperName=(TextView) view.findViewById(R.id.my_paper_name);
        TextView paperBuildTime=(TextView) view.findViewById(R.id.my_paper_time);
        try {
            paperId.setText("000"+mData.getJSONObject(i).getString("exam_id"));
            paperName.setText(mData.getJSONObject(i).getString("exam_name"));
            Date date =new Date(mData.getJSONObject(i).getLong("exam_time"));//转成时间格式
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//设置转化格式
            String time=sdf.format(date);
            paperBuildTime.setText(time);
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
