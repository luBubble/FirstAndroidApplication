package com.zl.tesseract.scanner.student.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.more.MyAddApplyFragement;
import com.zl.tesseract.scanner.more.StudentApplyFragment;
import com.zl.tesseract.scanner.more.TeacherMessageFragment;

public class StudentMoreFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StudentMorePageAdapter studentMorePageAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        studentMorePageAdapter=new StudentMorePageAdapter(getChildFragmentManager(),getContext());
        studentMorePageAdapter.addFragment(new StudentOwnMessaageFragment(),"个人信息");
        studentMorePageAdapter.addFragment(new MyAddApplyFragement(),"我的申请");
        studentMorePageAdapter.addFragment(new StudentApplyFragment(),"添加验证");
        viewPager.setAdapter(studentMorePageAdapter);
        tabLayout.setupWithViewPager(viewPager);//双向绑定
    }
}
