package com.zl.tesseract.scanner.student.myTeacherAndParent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zl.tesseract.scanner.bean.Teacher;

import java.util.List;

public class tpListAdapter extends BaseAdapter {
 private List<Teacher> mData;
 private Context mContext;

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

        return null;
    }
}
