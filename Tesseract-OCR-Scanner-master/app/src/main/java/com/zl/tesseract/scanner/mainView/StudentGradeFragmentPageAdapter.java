package com.zl.tesseract.scanner.mainView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class StudentGradeFragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mlist=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private Context Context;

    public StudentGradeFragmentPageAdapter(FragmentManager fm,Context Context) {
        super(fm);
        this.Context=Context;
    }

    public void addFragment(Fragment fragment,String title)
    {
        mlist.add(fragment);
        titleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return  mlist.get(position);//显示第几个页面
    }

    @Override
    public int getCount() {
        return mlist.size();//有几个页面
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
