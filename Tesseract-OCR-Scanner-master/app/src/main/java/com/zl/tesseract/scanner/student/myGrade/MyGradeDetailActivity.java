package com.zl.tesseract.scanner.student.myGrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.mainView.MyStudentGradeDetailFragment;

public class MyGradeDetailActivity extends AppCompatActivity {
    private TextView btn_back;
    private Toolbar toolbar;
    private FrameLayout no_menu_framelayout;
    private TextView txt_toolbar;
    private Fragment gradeDetail;
    private FragmentManager fManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_menu_frame);
        Bundle bd=getIntent().getExtras();//拿数据
        bindView();
        txt_toolbar.setText("成绩详情");
        fManager = getSupportFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        gradeDetail= new MyStudentGradeDetailFragment(fManager);
        gradeDetail.setArguments(bd);
        fTransaction.replace(R.id.no_menu_framelayout, gradeDetail);
        fTransaction.commit();
    }

    private void bindView()
    {
        toolbar=(Toolbar)findViewById(R.id.no_menu_frame_toolbar) ;
        txt_toolbar=(TextView)toolbar.findViewById(R.id.txt_toolbar) ;
        btn_back=(TextView)toolbar.findViewById(R.id.btn_back) ;
        no_menu_framelayout=(FrameLayout)findViewById(R.id.no_menu_framelayout) ;
        //返回
        btn_back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            finish();

                    }
                }
        );
    }

}
