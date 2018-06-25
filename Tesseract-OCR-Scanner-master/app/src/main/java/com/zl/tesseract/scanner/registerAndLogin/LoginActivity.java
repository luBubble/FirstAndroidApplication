package com.zl.tesseract.scanner.registerAndLogin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zl.tesseract.R;
import com.zl.tesseract.scanner.mainView.MenuActivity;
import com.zl.tesseract.scanner.student.MainViewStudentActivity;
import com.zl.tesseract.scanner.tool.*;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {
    private TextView login_user_id;
    private TextView login_user_password;
    private TextView link_register;
    private Button btn_login;
    private int teacherId;
    private String password;
    final Pattern phonePattern = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.login);
        bindViews();
    }

    private void bindViews()
    {
        login_user_id=(TextView)findViewById(R.id.login_user_id);
        login_user_password=(TextView)findViewById(R.id.login_user_password);
        link_register=(TextView)findViewById(R.id.link_register);
        btn_login=(Button)findViewById(R.id.btn_login) ;

        link_register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                        startActivity(intent);
                    }
                }
        );
        btn_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login();
                    }
                }
        );

    }

    public void login() {
        if (TextUtils.isEmpty(login_user_id.getText())) {
            Toast.makeText(LoginActivity.this, "账号不能为空！", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(login_user_password.getText())) {
            Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }else if(!phonePattern.matcher(login_user_id.getText().toString().trim()).matches()){
            Toast.makeText(LoginActivity.this, "账号不正确！", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
           new Thread() {
                public void run() {
                    try {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("teacher_account", login_user_id.getText().toString());
                        String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/oneTeacherByAccount.do", map);
                        Log.e("登录===",str);
                        if(str!=null&&str!="[]"&&!"".equals(str))
                        {
                            JSONObject jsonObject = new JSONObject(str);
                            teacherId = jsonObject.getInt("teacher_id");
                            password = jsonObject.getString("teacher_password");
                            if (login_user_password.getText().toString().equals(password))
                            {
                                handler.sendEmptyMessage(0x124);
                            } else {
                                // 登录失败
                                handler.sendEmptyMessage(0x123);
                            }
                        }
                        else
                        { // 登录失败
                            handler.sendEmptyMessage(0x122);
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private Handler handler = new Handler() {
        @SuppressLint("WrongConstant")
        public void handleMessage(android.os.Message msg) {
            Bundle bd=new Bundle();
            bd.putInt("teacherId",teacherId);
            if (msg.what == 0x124) {
                Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MenuActivity.class);
                intent.putExtras(bd);
                startActivity(intent);
            } else if (msg.what == 0x123) {
                Toast.makeText(LoginActivity.this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
            }
            else if (msg.what == 0x122) {
                Toast.makeText(LoginActivity.this, "该账户不存在,请先注册!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        };
    };
}



