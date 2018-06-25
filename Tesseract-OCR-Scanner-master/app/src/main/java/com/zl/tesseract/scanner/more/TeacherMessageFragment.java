package com.zl.tesseract.scanner.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.bean.Student;
import com.zl.tesseract.scanner.bean.Teacher;
import com.zl.tesseract.scanner.mainView.StudentAdapter;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TeacherMessageFragment extends Fragment {
    private TextView name;
    private TextView id;
    private TextView school;
    private TextView phone;
    private  TextView description;
    private Button btn_edit_msg;
    private Button btn_edit_psw;
    //JSONObject jsonObject = new JSONObject();

//    private Teacher mData;
//    private TeacherAdapter mAdapter;
    private String name1;
    private String id1;
    private String phone1;
    private String description1;
    private int teacherId;
   // Handler handler=new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        teacherId=getArguments().getInt("teacherId");
        View view = inflater.inflate(R.layout.teacher_message, container, false);
        name= (TextView) view.findViewById(R.id.user_msg_name);
        id=(TextView)view.findViewById(R.id.user_msg_id);
        school=(TextView)view.findViewById(R.id.user_msg_school);
        phone=(TextView)view.findViewById(R.id.user_msg_phone);
        description=(TextView)view.findViewById(R.id.user_msg_description);
        btn_edit_msg=(Button) view.findViewById(R.id.btn_edit_msg);
        btn_edit_psw=(Button) view.findViewById(R.id.btn_edit_psw);

        //修改资料
        btn_edit_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editMsg=new Intent(getActivity(),TeacherEditMsgActivity.class);
                Bundle bd=new Bundle();
                bd.putString("name",name.getText().toString());
                bd.putString("tel",phone.getText().toString());
                bd.putString("school",school.getText().toString());
                bd.putString("description",description.getText().toString());
                bd.putInt("teacherId",teacherId);
                editMsg.putExtras(bd);
                startActivity(editMsg);
            }
        });
//修改密码
        btn_edit_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPsw=new Intent(getActivity(),TeacherEditPswActivity.class);
                Bundle bd=new Bundle();
                bd.putInt("teacherId",teacherId);
                editPsw.putExtras(bd);
                startActivity(editPsw);

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetTeacherInformation();
    }

    public void GetTeacherInformation() {
        //list.removeAll(list);
        new Thread() {
                public void run () {
                    try {
                        Map<String, Object> map = new HashMap<String, Object>();
                        String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/oneTeacher/"+teacherId+".do", map);
                        Log.e("xianshi", str);
                        JSONObject jsonObject = new JSONObject(str);
                        name1=jsonObject.getString("teacher_name");
                        id1=jsonObject.getString("teacher_tel");//账号为手机号
                        phone1=jsonObject.getString("teacher_tel");
                        description1=jsonObject.getString("teacher_description");
//                        name.setText(jsonObject.getString("teacher_name"));
//                        id.setText(jsonObject.getString("teacher_num"));
//                        phone.setText(jsonObject.getString("teacher_tel"));
//                        description.setText(jsonObject.getString("teacher_description"));
                        handler.sendEmptyMessage(0x222);
                    } catch (Exception e) {
                        handler.sendEmptyMessage(0x222);
                        Log.e("xianshi", "发生异常");
                        e.printStackTrace();
                    }
                }

        }.start();
    }



  private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x222) {
                if(name1.equals("null"))
                {
                    name.setText("");
                }else
                {
                    name.setText(name1);
                }
                if(id1.equals("null"))
                {
                    id.setText("");
                }else
                {
                    id.setText(id1);
                }
                if(phone1.equals("null"))
                {
                    phone.setText("");
                }else
                {
                    phone.setText(phone1);
                }
                if(description1.equals("null"))
                {
                    description.setText("");
                }else
                {
                    description.setText(description1);
                }

            }

        };
    };


}




