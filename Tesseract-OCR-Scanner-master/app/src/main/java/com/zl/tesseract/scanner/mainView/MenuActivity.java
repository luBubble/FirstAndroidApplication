package com.zl.tesseract.scanner.mainView;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.more.TeacherMoreFragment;
import com.zl.tesseract.scanner.myPaper.MyPaperFragment;
import com.zl.tesseract.scanner.registerAndLogin.LoginActivity;
import com.zl.tesseract.scanner.search.AddPaperActivity;
import com.zl.tesseract.scanner.search.AddStudentActivity;
import com.zl.tesseract.scanner.search.SearchStudentToAddActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    //UI Object
    private TextView txt_toolbar;
    private TextView txt_paper;
    private TextView txt_student;
    private TextView txt_more;
    private TextView btn_more;
    private Toolbar toolbar;
    private FrameLayout fg_content;
    private PopupWindow mPopupWindow;
    private FloatingActionButton btn_scan;

    //Fragment Object
    private Fragment fg1,fg2,fg3;
    private FragmentManager fManager;

    private int teacherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不带标题
        setContentView(R.layout.main_view);
        teacherId=getIntent().getExtras().getInt("teacherId");
        fManager = getSupportFragmentManager();
        bindViews();
        txt_student.setSelected(true);   //进去选择第一项
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        toolbar=(Toolbar)findViewById(R.id.main_view_toolbar);
        txt_toolbar = (TextView) findViewById(R.id.txt_toolbar);
        txt_student = (TextView) findViewById(R.id.txt_student);
        txt_more  = (TextView) findViewById(R.id.txt_more);
        txt_paper=(TextView)findViewById(R.id.txt_paper) ;
        fg_content = (FrameLayout) findViewById(R.id.fg_content);
        btn_more=(TextView)findViewById(R.id.btn_more);
        btn_scan=(FloatingActionButton)findViewById(R.id.btn_scan) ;

        //更多菜单监听事件
        toolbar.findViewById(R.id.btn_more).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popUpMore();//弹出下拉列表
                    }
                }
        );

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MenuActivity.this, com.zl.tesseract.scanner.ScannerActivity.class);
                intent.putExtras(getIntent().getExtras());
                startActivity(intent);
            }
        });
        txt_paper.setOnClickListener(this);
        txt_student.setOnClickListener(this);
        txt_more.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_paper.setSelected(false);
        txt_student.setSelected(false);
        txt_more.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        if(txt_student.isSelected())
        {
            if(fg1 == null){
                fg1 = new MyStudentFragment();
                fg1.setArguments(getIntent().getExtras());
                fTransaction.add(R.id.fg_content,fg1);
            }else{
                fTransaction.show(fg1);
            }
        }
        if(txt_paper.isSelected())
        {
            if(fg2 == null){
                fg2= new MyPaperFragment();
                fg2.setArguments(getIntent().getExtras());
                fTransaction.add(R.id.fg_content,fg2);
            }else{
                fTransaction.show(fg2);
            }
        }
        if(txt_more.isSelected())
        {
            if(fg3 == null){
                // fg3= new StudentGradeFragment();
                fg3= new TeacherMoreFragment();
                fg3.setArguments(getIntent().getExtras());
                fTransaction.add(R.id.fg_content,fg3);
            }else{
                fTransaction.show(fg3);
            }
        }
        fTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_student:
                setSelected();
                txt_student.setSelected(true);//选中我的学生
                txt_toolbar.setText("我的学生");
                if(fg1 == null){
                    fg1 = new MyStudentFragment();
                    fg1.setArguments(getIntent().getExtras());
                    fTransaction.add(R.id.fg_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;

            case R.id.txt_paper://选中我的试卷
                setSelected();
                txt_paper.setSelected(true);
                txt_toolbar.setText("我的试卷");
                if(fg2 == null){
                    fg2= new MyPaperFragment();
                    fg2.setArguments(getIntent().getExtras());
                    fTransaction.add(R.id.fg_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;

            case R.id.txt_more://选中更多
                setSelected();
                txt_more.setSelected(true);
                txt_toolbar.setText("更多");
                if(fg3 == null){
                   // fg3= new StudentGradeFragment();
                    fg3= new TeacherMoreFragment();
                    fg3.setArguments(getIntent().getExtras());
                    fTransaction.add(R.id.fg_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
//右上角更多
            case R.id.btn_more_add_user:
//                Intent addUser=new Intent(MenuActivity.this,SearchStudentToAddActivity.class);
//                startActivity(addUser);
                Intent addStudent=new Intent(MenuActivity.this,AddStudentActivity.class);
                addStudent.putExtras(getIntent().getExtras());
                startActivity(addStudent);
                break;
            case R.id.btn_more_add_paper:
                Intent addPaper=new Intent(MenuActivity.this,AddPaperActivity.class);
                addPaper.putExtras(getIntent().getExtras());
                startActivity(addPaper);
                break;
            case R.id.btn_more_out_login:
                Intent outLogin=new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(outLogin);
                Toast.makeText(MenuActivity.this,"成功退出登录",Toast.LENGTH_LONG).show();
                break;
        }
        //点击PopWindow的item后,关闭此PopWindow
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        fTransaction.commit();
    }

    /**
     * 弹出更多菜单
     */
    public void popUpMore() {
        //获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //状态栏高度+toolbar的高度
        int yOffset = frame.top + toolbar.getHeight();
        if (null == mPopupWindow) {
            //初始化PopupWindow的布局
            View popView = getLayoutInflater().inflate(R.layout.btn_more_list, null);
            //popView即popupWindow的布局，ture设置focusAble.
            mPopupWindow = new PopupWindow(popView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            //点击外部关闭。
            mPopupWindow.setOutsideTouchable(true);
            //设置一个动画。
            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            //设置Gravity，让它显示在右上角。
            mPopupWindow.showAtLocation(toolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
            //关闭时去掉遮罩
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
            //设置item的点击监听
            backgroundAlpha(0.4f);
            popView.findViewById(R.id.btn_more_add_user).setOnClickListener(this);
            popView.findViewById(R.id.btn_more_add_paper).setOnClickListener(this);
            popView.findViewById(R.id.btn_more_out_login).setOnClickListener(this);
            }
            else {
            mPopupWindow.showAtLocation(toolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
                }

    }

    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }

}
