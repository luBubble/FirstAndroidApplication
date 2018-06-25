package com.zl.tesseract.scanner.more;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeacherMorePageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mlist=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private Context Context;

    public TeacherMorePageAdapter(FragmentManager fm, android.content.Context context) {
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
