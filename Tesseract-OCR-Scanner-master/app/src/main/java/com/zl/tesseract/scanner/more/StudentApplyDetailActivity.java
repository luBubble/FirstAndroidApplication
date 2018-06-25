package com.zl.tesseract.scanner.more;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;

public class StudentApplyDetailActivity extends Activity {
    private Toolbar toolbar;
    private TextView back;
    private TextView name;
    private TextView id;
    private TextView school;
    private TextView studentClass;
    private TextView phone;
    private TextView age;
    private TextView sex;
    private Button btn_agree;
    private Button btn_refuse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_apply_detail);
        bindView();
    }

    private  void  bindView()
    {
        name= (TextView) findViewById(R.id.user_msg_name);
        id=(TextView)findViewById(R.id.user_msg_id);
        school=(TextView)findViewById(R.id.user_msg_school);
        studentClass=(TextView)findViewById(R.id.user_msg_class);
        phone=(TextView)findViewById(R.id.user_msg_phone);
        age=(TextView)findViewById(R.id.user_msg_age);
        sex=(TextView)findViewById(R.id.user_msg_sex);
        name.setText(getIntent().getExtras().getString("name"));//获取传递过来的Bundle对象
        id.setText(getIntent().getExtras().getString("id"));
        school.setText(getIntent().getExtras().getString("school"));
        studentClass.setText(getIntent().getExtras().getString("studentClass"));
        phone.setText(getIntent().getExtras().getString("phone"));
        age.setText(getIntent().getExtras().getString("age"));
        sex.setText(getIntent().getExtras().getString("sex"));

        toolbar=(Toolbar)findViewById(R.id.apply_msg_toolbar);
        back=(TextView)toolbar.findViewById(R.id.btn_back);
        btn_agree=(Button)findViewById(R.id.btn_agree);
        btn_refuse=(Button)findViewById(R.id.btn_refuse);

        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //同意
        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentApplyDetailActivity.this,"已同意",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        //拒绝
        btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentApplyDetailActivity.this,"已拒绝",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
