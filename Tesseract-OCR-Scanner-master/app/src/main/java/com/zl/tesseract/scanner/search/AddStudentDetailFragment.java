package com.zl.tesseract.scanner.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;

public class AddStudentDetailFragment extends Fragment {
    private TextView name;
    private TextView id;
    private TextView school;
    private TextView studentClass;
    private TextView phone;
    private TextView age;
    private TextView sex;
    private Button btn;
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
        btn.setText("添加学生");
        name.setText(getArguments().getString("name"));//getArgument获取传递过来的Bundle对象
        id.setText(getArguments().getString("id"));
        school.setText(getArguments().getString("school"));
        studentClass.setText(getArguments().getString("studentClass"));
        phone.setText(getArguments().getString("phone"));
        age.setText(getArguments().getString("age"));
        sex.setText(getArguments().getString("sex"));

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String studentId=id.getText().toString();
                Toast.makeText(getActivity(),"已发送申请",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
