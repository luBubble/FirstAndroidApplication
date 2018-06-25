package com.zl.tesseract.scanner.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.tool.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class AddPaperActivity extends AppCompatActivity {
    private EditText paper_name;
    private Button btn_save;
    private Button btn_cancel;
    private Toolbar toolbar;
    private TextView back;
    private int teacherId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teacherId=getIntent().getExtras().getInt("teacherId");
        setContentView(R.layout.add_paper);
        paper_name=(EditText)findViewById(R.id.paper_name);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_cancel=(Button)findViewById(R.id.btn_cancel);
        toolbar=(Toolbar)findViewById(R.id.add_paper_toolbar);
        back=(TextView)toolbar.findViewById(R.id.btn_back);
        //新增试卷
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run()
                    {
                        String newPaperName=paper_name.getText().toString();
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("exam_name",newPaperName);
                        map.put("teacher_id",String.valueOf(teacherId));
                        String str = HttpUtil.doPost(HttpUtil.path+"homework/Exam/saveExam.do", map);
                    }}.start();
                Toast.makeText(AddPaperActivity.this,"添加试卷成功",Toast.LENGTH_LONG).show();
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
