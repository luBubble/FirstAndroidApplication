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
import com.zl.tesseract.scanner.myPaper.PaperAdapter;
import com.zl.tesseract.scanner.tool.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class PickExamActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView paperList;
    private TextView back;
    private TextView headline;
    private PaperAdapter paperAdapter;
    private JSONArray paperJSONArray;
    private Handler handler;
    private int pickPaperId;
    private int teacherId=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_button);
        handler=new Handler();
        teacherId=getIntent().getExtras().getInt("teacherId");
        bindview();
        GetAllExamInformation();
    }
    private void bindview()
    {
        paperList=(ListView)findViewById(R.id.pick_student_list);
        paperList.setOnItemClickListener(this);//绑定item点击监听
        back=(TextView)findViewById(R.id.btn_back);
        headline=(TextView)findViewById(R.id.txt_toolbar);
        headline.setText("选择试卷");
        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            pickPaperId=paperJSONArray.getJSONObject(i).getInt("exam_id");
            new Thread() {
                public void run() {
                    Bundle gradeMsg=getIntent().getExtras();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("student_id",String.valueOf(gradeMsg.get("student_id")));
                    map.put("teacher_id",String.valueOf(gradeMsg.get("teacherId")));
                    map.put("exam_id",String.valueOf(pickPaperId));
                    map.put("grade_num",String.valueOf(gradeMsg.get("grade")));
                    map.put("grade_wrong",String.valueOf(gradeMsg.get("wrong")));
                    String str = HttpUtil.doPost(HttpUtil.path+"homework/Exam/saveGrade.do", map);
                    }
            }.start();


            Toast.makeText(PickExamActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //获取试卷数据
    public void GetAllExamInformation() {
        //list.removeAll(list);
        new Thread() {
            public void run() {
                String getExamsUrl= HttpUtil.path+"homework/Exam/allExamsOfTeacher/"+teacherId+".do";
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String str = HttpUtil.doPost(getExamsUrl, map);
                    if(str!=null&&!str.equals("[]")&&!"".equals(str))
                    {
                        paperJSONArray=new JSONArray(str);
                        //获取试卷数据
                        paperAdapter=new PaperAdapter(paperJSONArray,PickExamActivity.this);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                paperList.setAdapter(paperAdapter);
                            }
                        });
                    }
                    else
                    {
                        Looper.prepare();
                        Toast.makeText(PickExamActivity.this, "先去添加试卷吧！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (Exception e) {
                    Log.e("PickExamActivity","发生异常");
                    e.printStackTrace();
                }}
        }.start();
    }
}
