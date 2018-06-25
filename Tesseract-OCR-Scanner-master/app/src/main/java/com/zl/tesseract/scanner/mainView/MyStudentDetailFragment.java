package com.zl.tesseract.scanner.mainView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.more.RoundImageView;

public class MyStudentDetailFragment extends Fragment {
    private FragmentManager fManager;
    private TextView name;
    private TextView id;
    private TextView school;
    private TextView studentClass;
    private TextView phone;
    private TextView age;
    private TextView sex;
    private Button btn;
    private RoundImageView pic;

    @SuppressLint("ValidFragment")
    public MyStudentDetailFragment(FragmentManager fManager) {
        this.fManager = fManager;
    }

    public MyStudentDetailFragment(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_message, container, false);
        btn=(Button)view.findViewById(R.id.studenr_msg_btn) ;
        name= (TextView) view.findViewById(R.id.user_msg_name);
        id=(TextView)view.findViewById(R.id.user_msg_id);
        school=(TextView)view.findViewById(R.id.user_msg_school);
        studentClass=(TextView)view.findViewById(R.id.user_msg_class);
        phone=(TextView)view.findViewById(R.id.user_msg_phone);
        age=(TextView)view.findViewById(R.id.user_msg_age);
        sex=(TextView)view.findViewById(R.id.user_msg_sex);
        name.setText(getArguments().getString("name"));//getArgument获取传递过来的Bundle对象
        id.setText(getArguments().getString("id"));
        school.setText(getArguments().getString("school"));
        studentClass.setText(getArguments().getString("studentClass"));
        phone.setText(getArguments().getString("phone"));
        age.setText(getArguments().getString("age"));
        sex.setText(getArguments().getString("sex"));
        if(getArguments().getString("type").equals("apply"))
        {
            Button btn=(Button)view.findViewById(R.id.studenr_msg_btn);
            btn.setText("已同意");
            btn.setEnabled(false);
        }
//        int image[] = new int[]{
//                R.mipmap.s0,
//                R.mipmap.s1,
//                R.mipmap.s2,
//                R.mipmap.s3,
//                R.mipmap.s4,
//                R.mipmap.s0,
//                R.mipmap.s1,
//                R.mipmap.s2,
//                R.mipmap.s3,
//                R.mipmap.s4,
//                R.mipmap.s0,
//                R.mipmap.s1,
//                R.mipmap.s2,
//                R.mipmap.s3,
//                R.mipmap.s4,
//        };
//        pic=(RoundImageView)view.findViewById(R.id.user_msg_pic);
//        pic.setImageResource(image[getArguments().getInt("pic")]);

        //查看学生成绩
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                MyStudentGradeFragment studentGradeFragment = new MyStudentGradeFragment(fManager);
                studentGradeFragment.setArguments(getArguments());//把当前数据传过去
                //获取Activity的控件
                TextView txt_toolbar = (TextView) getActivity().findViewById(R.id.txt_toolbar);
                txt_toolbar.setText("学生成绩");
                fTransaction.replace(R.id.no_menu_framelayout, studentGradeFragment);
                //调用addToBackStack将Fragment添加到栈中
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });
        return view;
    }
}
