package com.zl.tesseract.scanner.more;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.tool.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TeacherEditMsgActivity extends Activity {
    private Toolbar toolbar;
    private TextView back;
    private EditText name;
    private TextView id;
    private  EditText school;
    private EditText  phone;
    private EditText description;
    private Button btn_save;
    private  Button btn_cancel;
    private int teacherId;

    private String newName;
    private String newSchool;
    private String newPhone;
    private String newDescription;

    final Pattern phonePattern = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_edit_msg);
        teacherId=getIntent().getExtras().getInt("teacherId");
        bindView();
        setData();
    }
    public void bindView()
    {
        toolbar=(Toolbar)findViewById(R.id.edit_msg_toolbar);
        back=(TextView)toolbar.findViewById(R.id.btn_back);
        id=(TextView)findViewById(R.id.user_msg_id) ;
        name=(EditText)findViewById(R.id.edit_msg_name);
        school=(EditText)findViewById(R.id.edit_msg_school);
        phone=(EditText)findViewById(R.id.edit_msg_phone);
        description=(EditText)findViewById(R.id.edit_msg_description);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_cancel=(Button) findViewById(R.id.btn_cancel);
        //保存修改
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMsg();
                finish();
            }
        });
        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //取消
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void setData()
    {
        name.setText(getIntent().getExtras().getString("name"));
        phone.setText(getIntent().getExtras().getString("tel"));
        description.setText(getIntent().getExtras().getString("description"));
        id.setText(getIntent().getExtras().getString("tel"));
        school.setText(getIntent().getExtras().getString("school"));
    }
    private void editMsg()
    {
        newName=name.getText().toString().trim();
        newSchool=school.getText().toString().trim();
        newPhone=phone.getText().toString().trim();
        newDescription=description.getText().toString().trim();
        if(newName.equals("")||newSchool.equals("")||newPhone.equals("")||newDescription.equals(""))
        {
            handler.sendEmptyMessage(0x224);
        }
        else if(!phonePattern.matcher(newPhone).matches())
        {
            handler.sendEmptyMessage(0x223);
        }
        else
        {
            new Thread(){public void run(){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tel",newPhone);
                map.put("name",newName);
                map.put("teacherId",teacherId);
                map.put("description",newDescription);
                map.put("school",newSchool);
                String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/updateTeacher.do", map);
            }}.start();

            handler.sendEmptyMessage(0x222);
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x222) {
                Toast.makeText(TeacherEditMsgActivity.this,"成功修改资料",Toast.LENGTH_LONG).show();
            }
            if (msg.what == 0x223) {
                Toast.makeText(TeacherEditMsgActivity.this,"手机号码格式不正确！",Toast.LENGTH_LONG).show();Toast.makeText(TeacherEditMsgActivity.this,"成功修改资料",Toast.LENGTH_LONG).show();
            }
            if (msg.what == 0x224) {
                Toast.makeText(TeacherEditMsgActivity.this,"请将信息填写完整！",Toast.LENGTH_LONG).show();
            }
        };
    };

}
