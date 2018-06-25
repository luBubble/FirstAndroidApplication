package com.zl.tesseract.scanner.student.more;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentOwnMessaageFragment extends Fragment {
    private TextView name;
    private TextView id;
    private TextView school;
    private TextView phone;
    private  TextView classid;
    private  TextView age;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_own_message, container, false);
        name= (TextView) view.findViewById(R.id.user_msg_name);
        id=(TextView)view.findViewById(R.id.user_msg_id);
        school=(TextView)view.findViewById(R.id.user_msg_school);
        classid=(TextView)view.findViewById(R.id.user_msg_class);
        phone=(TextView)view.findViewById(R.id.user_msg_phone);
        age=(TextView)view.findViewById(R.id.user_msg_description);
        description=(TextView)view.findViewById(R.id.user_msg_description);
        btn_edit_msg=(Button) view.findViewById(R.id.btn_edit_msg);
        btn_edit_psw=(Button) view.findViewById(R.id.btn_edit_psw);

        GetStudentInformation();
        return view;
    }
    public void GetStudentInformation() {
        //list.removeAll(list);
        new Thread() {
            public void run () {
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    // map.put("type", "学生");
                    String str = HttpUtil.doPost(HttpUtil.path+"homework/Student/oneTeacher/1.do", map);
                    Log.e("xianshi", str);
                    JSONObject jsonObject = new JSONObject(str);
                    name1=jsonObject.getString("teacher_name");
                    id1=jsonObject.getString("teacher_num");
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
                name.setText(name1);
                id.setText(id1);
                phone.setText(phone1);
                description.setText(description1);

            }

        };
    };


}
