package com.zl.tesseract.scanner.myPaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zl.tesseract.R;

public class MyPaperGradeActivity extends AppCompatActivity {
    private TextView btn_back;
    private Toolbar toolbar;
    private FrameLayout no_menu_framelayout;
    private TextView txt_toolbar;
    private Fragment paperGradeList,GradeDetail;
    private FragmentManager fManager;
    private int teacherId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_menu_frame);
        Bundle bd=getIntent().getExtras();//拿数据
        teacherId=bd.getInt("teacherId");
        Log.e("试卷成绩==teacherId",String.valueOf(teacherId));
        bindView();
        txt_toolbar.setText("学生成绩");
        fManager = getSupportFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        paperGradeList= new MyPaperGradeListFragment(fManager);
        paperGradeList.setArguments(bd);
        fTransaction.replace(R.id.no_menu_framelayout, paperGradeList);
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
                        if (fManager.getBackStackEntryCount() == 0) {
                            finish();
                        } else {
                            fManager.popBackStack();
                            txt_toolbar.setText("学生信息");
                        }
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (fManager.getBackStackEntryCount() == 0) {
            finish();
        } else {
            fManager.popBackStack();
            txt_toolbar.setText("学生信息");
        }
    }

}
