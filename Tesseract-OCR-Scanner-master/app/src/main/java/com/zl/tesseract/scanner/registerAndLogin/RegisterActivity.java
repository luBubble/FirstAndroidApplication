package com.zl.tesseract.scanner.registerAndLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.mainView.MenuActivity;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {
    private EditText register_new_user;
    private EditText register_new_password;
    private EditText register_confirm_password;
    private TextView link_login;
    private Button btn_register;
    final Pattern emailPattern = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    final Pattern phonePattern = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");

    private String new_account;
    private String new_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        bindViews();
    }

    private void bindViews()
    {
        register_new_user=(EditText) findViewById(R.id.register_new_user);
        register_new_password=(EditText) findViewById(R.id.register_new_password);
        register_confirm_password=(EditText) findViewById(R.id.register_confirm_password);
        link_login=(TextView)findViewById(R.id.link_login);
        btn_register=(Button)findViewById(R.id.btn_register);

        link_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );
        //注册
        btn_register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //trim()去掉首尾空格
                        new_account=register_new_user.getText().toString().trim();
                        new_password=register_new_password.getText().toString().trim();
                        String confirm_password=register_confirm_password.getText().toString().trim();
                        if(new_account.equals("")||new_password.equals("")||confirm_password.equals(""))
                        {
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("提示").setMessage("请将信息填写完整！")
                                    .setPositiveButton("确定", null).show();
                        }
                        else if(!emailPattern.matcher(new_account).matches()&&!phonePattern.matcher(new_account).matches())
                        {
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("提示").setMessage("请输入正确的手机号码或邮箱！")
                                    .setPositiveButton("确定", null).show();
                        }
                        else if(!new_password.equals(confirm_password))
                        {
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("提示").setMessage("密码与确认密码不一致！")
                                    .setPositiveButton("确定", null).show();
                        }
                        else
                        {
                            new Thread() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                public void run() {
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("teacher_account", new_account);
                                    map.put("teacher_password", new_password);
                                    HttpUtil.doPost(HttpUtil.path+"homework/Student/teacherRigister.do", map);
                                    map.remove("teacher_password", new_password);
                                    String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/oneTeacherByAccount.do", map);
                                    if(str!=null)
                                    {
                                        try {
                                            JSONObject jsonObject = new JSONObject(str);
                                            Bundle bd=new Bundle();
                                            bd.putInt("teacherId",jsonObject.getInt("teacher_id"));
                                            Intent intent=new Intent(RegisterActivity.this,MenuActivity.class);
                                            intent.putExtras(bd);
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }.start();
                        }

                    }
                }
        );
    }

}
