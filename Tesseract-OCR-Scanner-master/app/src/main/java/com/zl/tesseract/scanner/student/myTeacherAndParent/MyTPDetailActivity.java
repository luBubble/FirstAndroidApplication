package com.zl.tesseract.scanner.student.myTeacherAndParent;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zl.tesseract.R;

public class MyTPDetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_teacher_detail);
        TextView btn=(TextView)findViewById(R.id.btn_back);
        //返回
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       finish();
                    }
                }
        );
    }

}
