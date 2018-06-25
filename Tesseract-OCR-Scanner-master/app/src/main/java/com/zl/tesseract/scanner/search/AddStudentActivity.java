package com.zl.tesseract.scanner.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.mainView.MenuActivity;
import com.zl.tesseract.scanner.registerAndLogin.LoginActivity;
import com.zl.tesseract.scanner.registerAndLogin.RegisterActivity;
import com.zl.tesseract.scanner.tool.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {
    private EditText user_student_name;
    private EditText user_school_name;
    private EditText user_class_name;
    private EditText user_student_tel;
    private EditText user_student_age;
    private TextView user_student_sex;
    private EditText user_student_account;
    private Button btn_save_student;
    private Button btn_cancel_student;
    private Toolbar toolbar;
    private TextView back;
    private int teacherId;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        teacherId=getIntent().getExtras().getInt("teacherId");
        bindView();
    }
    private void bindView()
    {
        user_student_name=(EditText) findViewById(R.id.user_student__name);
        user_school_name=(EditText)findViewById(R.id.user_school_name);
        user_class_name=(EditText)findViewById(R.id.user_class_name);
        user_student_tel=(EditText)findViewById(R.id.user_student_tel);
        user_student_age=(EditText)findViewById(R.id. user_student_age);
        user_student_sex=(TextView) findViewById(R.id.user_student_sex);
        user_student_account=(EditText)findViewById(R.id.user_student_account);
        btn_save_student=(Button)findViewById(R.id.btn_save_student);
        btn_cancel_student=(Button)findViewById(R.id.btn_cancel_student);
        toolbar=(Toolbar)findViewById(R.id.add_paper_toolbar);
        back=(TextView)toolbar.findViewById(R.id.btn_back);
        //新增试卷
        btn_save_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
                finish();
            }
        });
        //取消
        btn_cancel_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //选择性别
        user_student_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sexDialog();
            }
        });
    }

    private void addStudent()
    {
        new Thread(){
            public void run(){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("teacher_id", String.valueOf(teacherId));
                map.put("shcool_name",user_school_name.getText().toString().trim());
                map.put("class_name", user_class_name.getText().toString().trim());
                map.put("student_name", user_student_name.getText().toString().trim());
                map.put("student_age", user_student_age.getText().toString().trim());
                map.put("student_sex", user_student_sex.getText().toString().trim());
                map.put("student_account", user_student_account.getText().toString().trim());
                map.put("student_tel",user_student_tel.getText().toString().trim() );
                String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/addStudent.do", map);
                Log.e("增加了一个学生",str);
                if("".equals(str)||str.equals("[]"))
                {
                    handler.sendEmptyMessage(0x124);
                }
                else
                {
                    handler.sendEmptyMessage(0x123);
                }
            }}.start();
    }
    private Handler handler = new Handler() {
        @SuppressLint("WrongConstant")
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x124) {
                Toast.makeText(AddStudentActivity.this, "添加学生成功!", 1).show();
            } else if (msg.what == 0x123) {
                Toast.makeText(AddStudentActivity.this, "服务器错误!", 1).show();
            }else if (msg.what == 0x122) {
                Toast.makeText(AddStudentActivity.this, "服务器错误!", 1).show();
            }
        }
    };
    //选择性别对话框
    private void sexDialog() {
        final String items[]={"男","女"};
        //dialog参数设置
        AlertDialog.Builder builder=new AlertDialog.Builder(AddStudentActivity.this);  //先得到构造器
        builder.setTitle("选择性别"); //设置标题
        //builder.setMessage("是否确认退出?"); //设置内容
        //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
        builder.setItems(items,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int i) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        user_student_sex.setText(items[i]);//显示所选性别
                    }
                });
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}


