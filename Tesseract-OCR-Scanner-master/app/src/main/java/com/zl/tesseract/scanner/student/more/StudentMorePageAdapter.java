package com.zl.tesseract.scanner.student.more;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentMorePageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mlist=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private android.content.Context Context;

    public StudentMorePageAdapter(FragmentManager fm, android.content.Context context) {
        super(fm);
        Context = context;
    }

    public void addFragment(Fragment fragment,String title)
    {
        mlist.add(fragment);
        titleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
