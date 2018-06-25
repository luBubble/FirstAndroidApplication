package com.zl.tesseract.scanner.saveGrade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.mainView.StudentAdapter;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class PickStudentActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView studentList;
    private TextView back;
    private StudentAdapter studentAdapter;
    private JSONArray studentJSONArray;
    private Handler handler;
    private int TeacherId;
    private int pickStudentId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_button);
        handler=new Handler();
        TeacherId=getIntent().getExtras().getInt("teacherId");
        bindview();
        GetAllStudentInformation();
    }
    private void bindview()
    {
        studentList=(ListView)findViewById(R.id.pick_student_list);
        studentList.setOnItemClickListener(this);//绑定item点击监听
        back=(TextView)findViewById(R.id.btn_back);
        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //选择学生
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            pickStudentId=studentJSONArray.getJSONObject(i).getInt("student_id");
            Bundle gradeMsg=getIntent().getExtras();
            gradeMsg.putInt("student_id",pickStudentId);
            Intent pickExam=new Intent(PickStudentActivity.this,PickExamActivity.class);
            pickExam.putExtras(gradeMsg);
            startActivity(pickExam);
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//读取数据
    public void GetAllStudentInformation() {
        //list.removeAll(list);
        new Thread() {
            public void run() {
                String getStudentsUrl=HttpUtil.path+"homework/Student/allStudentsOfTeacher/"+TeacherId+".do";
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String str = HttpUtil.doPost(getStudentsUrl, map);
                    if(str!=null&&!str.equals("[]")&&!"".equals(str))
                    {
                        studentJSONArray=new JSONArray(str);
                        //填充清单分类数据
                        studentAdapter=new StudentAdapter(studentJSONArray,PickStudentActivity.this);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                studentList.setAdapter(studentAdapter);
                            }
                        });
                    }
                    else
                    {
                        Looper.prepare();
                        Toast.makeText(PickStudentActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (Exception e) {
                    Log.e("PickStudentActivity","发生异常");
                    e.printStackTrace();
                }}
        }.start();
    }

}
