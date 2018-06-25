package com.zl.tesseract.scanner.mainView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.tesseract.R;

import java.util.List;

public class StudentGradeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> listFragment;
    private StudentGradeFragmentPageAdapter fragmentAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.student_grade,container,false);
        tabLayout=(TabLayout)view.findViewById(R.id.tab_student_grade);
        viewPager = (ViewPager) view.findViewById(R.id.student_grade_viewpager);
        if(viewPager!=null)
        {
           setupViewPager(viewPager);//炸了
        }
        return view;
    }

    private void setupViewPager(ViewPager viewPager)
    {
        fragmentAdapter=new StudentGradeFragmentPageAdapter(getChildFragmentManager(),getContext());
        fragmentAdapter.addFragment(new StudentGradeFragmentPageFragment(),"所有成绩");
        fragmentAdapter.addFragment(new StudentGradeFragmentPageFragmentTwo(),"我批改的");
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);//双向绑定
    }

}
