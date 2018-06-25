package com.zl.tesseract.scanner.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.tesseract.R;

public class TeacherMoreFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TeacherMorePageAdapter teacherMorePageAdapter;
    private int teacherId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        teacherId=getArguments().getInt("teacherId");
        View view=inflater.inflate(R.layout.teacher_more,container,false);
        tabLayout=(TabLayout) view.findViewById(R.id.tab_teacher_more);
        viewPager=(ViewPager)view.findViewById(R.id.teacher_more_viewpager);
        if(viewPager!=null)
        {
            setupViewPager(viewPager);
        }
        return view;
    }

    private void setupViewPager(ViewPager viewPager)
    {
        TeacherMessageFragment teacherMsg=new TeacherMessageFragment();
        teacherMsg.setArguments(getArguments());
        MyAddApplyFragement addApplyFragement=new MyAddApplyFragement();
        addApplyFragement.setArguments(getArguments());
        StudentApplyFragment studentApplyFragment=new StudentApplyFragment();
        studentApplyFragment.setArguments(getArguments());
        teacherMorePageAdapter=new TeacherMorePageAdapter(getChildFragmentManager(),getContext());
        teacherMorePageAdapter.addFragment(teacherMsg,"个人信息");
//        teacherMorePageAdapter.addFragment(addApplyFragement,"我的申请");
//        teacherMorePageAdapter.addFragment(studentApplyFragment,"添加验证");
        AboutUsFragment abooutUsFragment=new AboutUsFragment();
        teacherMorePageAdapter.addFragment(abooutUsFragment,"关于我们");
        viewPager.setAdapter(teacherMorePageAdapter);
        tabLayout.setupWithViewPager(viewPager);//双向绑定
    }

}
