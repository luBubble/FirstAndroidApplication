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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TeacherEditPswActivity extends Activity {
    private Toolbar toolbar;
    private TextView back;
    private EditText old_psw;
    private EditText new_psw;
    private EditText  confirm_psw;
    private Button btn_save;
    private  Button btn_cancel;
    private int teacherId;
    private  String oldPsw;
    private String inputOldPsw;
    private String newPsw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_edit_psw);
        teacherId=getIntent().getExtras().getInt("teacherId");
        toolbar=(Toolbar)findViewById(R.id.edit_psw_toolbar);
        back=(TextView)toolbar.findViewById(R.id.btn_back);
        old_psw=(EditText)findViewById(R.id.edit_psw_old) ;
        new_psw=(EditText)findViewById(R.id.edit_psw_new);
        confirm_psw=(EditText)findViewById(R.id.edit_psw_confirm);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_cancel=(Button) findViewById(R.id.btn_cancel);
        //保存
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputOldPsw=old_psw.getText().toString();
                newPsw=new_psw.getText().toString();
                String confirmPsw=confirm_psw.getText().toString();
                if(inputOldPsw!=null&&!inputOldPsw.equals(""))
                {
                    new Thread(){
                        public void run(){
                            Map<String, Object> map = new HashMap<String, Object>();
                            String teacherMsg = HttpUtil.doPost(HttpUtil.path+"homework/Student/oneTeacher/"+teacherId+".do", map);
                            Log.e("获取老师信息","===="+teacherMsg+teacherId);
                            if(teacherMsg!=null&&!teacherMsg.equals("[]")&&!"".equals(teacherMsg))
                            {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(teacherMsg);
                                    oldPsw= jsonObject.getString("teacher_password");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                handler.sendEmptyMessage(0x226);
                            }
                        }}.start();
                }
                else
                {
                    handler.sendEmptyMessage(0x222);
                }

                if("".equals(newPsw)||newPsw==null||confirmPsw.equals(""))
                {
                    handler.sendEmptyMessage(0x222);
                }
                else if (!inputOldPsw.equals(oldPsw))
                {
                    handler.sendEmptyMessage(0x224);
                }
                else if(!confirmPsw.equals(newPsw))
                {
                    handler.sendEmptyMessage(0x223);
                }
                else {
                    //修改密码
                    new Thread(){
                        public void run()
                        {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("teacherId",teacherId);
                            map.put("newPassword",newPsw);
                            String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/updateTeacherPassword.do", map);
                            Log.e("修改密码===",str);
                            handler.sendEmptyMessage(0x225);
                        }
                    }.start();
                }
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

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x222) {
                Toast.makeText(TeacherEditPswActivity.this,"请填写密码",Toast.LENGTH_LONG).show();
            }
            if (msg.what == 0x223) {
                Toast.makeText(TeacherEditPswActivity.this, "新密码与确认密码不一致！", Toast.LENGTH_LONG).show();
            }
            if (msg.what == 0x224) {
                Toast.makeText(TeacherEditPswActivity.this,"旧密码错误！",Toast.LENGTH_LONG).show();
            }
            if (msg.what == 0x225) {
                Toast.makeText(TeacherEditPswActivity.this,"密码修改成功！",Toast.LENGTH_LONG).show();
            }
            if (msg.what == 0x226) {
                Toast.makeText(TeacherEditPswActivity.this,"服务器错误！",Toast.LENGTH_LONG).show();
            }
        };
    };

}
