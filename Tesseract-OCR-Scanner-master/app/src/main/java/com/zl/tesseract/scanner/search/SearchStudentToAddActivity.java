package com.zl.tesseract.scanner.search;


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

public class SearchStudentToAddActivity extends AppCompatActivity {
    private TextView btn_back;
    private Toolbar toolbar;
    private FrameLayout no_menu_framelayout;
    private TextView txt_toolbar;
    private Fragment fgList,fgDetail;
    private FragmentManager fManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_menu_frame);
        bindView();
        txt_toolbar.setText("添加学生");
        fManager = getSupportFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fgList= new SearchStudentResultFragment(fManager);
        fTransaction.replace(R.id.no_menu_framelayout, fgList);
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
                            txt_toolbar.setText("添加学生");
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
            txt_toolbar.setText("添加学生");
        }
    }

}
