package com.zl.tesseract.scanner.student;

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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.registerAndLogin.LoginActivity;
import com.zl.tesseract.scanner.search.SearchStudentToAddActivity;
import com.zl.tesseract.scanner.student.more.StudentMoreFragment;
import com.zl.tesseract.scanner.student.myGrade.MyGradeFragment;
import com.zl.tesseract.scanner.student.myTeacherAndParent.MyTPFragment;

public class MainViewStudentActivity extends AppCompatActivity implements View.OnClickListener {

    //UI Object
    private TextView txt_toolbar;
    private TextView txt_grade;
    private TextView txt_teacher;
    private TextView txt_more;
    private TextView btn_more;
    private Toolbar toolbar;
    private FrameLayout fg_content;
    private PopupWindow mPopupWindow;
    private FloatingActionButton btn_scan;

    //Fragment Object
    private Fragment fg1,fg2,fg3;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view_student);
        fManager = getSupportFragmentManager();
        bindViews();
        txt_grade.performClick();   //模拟一次点击，既进去后选择第一项
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        toolbar=(Toolbar)findViewById(R.id.main_view_student_toolbar);
        txt_toolbar = (TextView) toolbar.findViewById(R.id.txt_toolbar);
        txt_grade = (TextView) findViewById(R.id.s_grade);
        txt_more  = (TextView) findViewById(R.id.s_more);
        txt_teacher=(TextView)findViewById(R.id.s_teacher);
        fg_content = (FrameLayout) findViewById(R.id.fg_content);
        btn_more=(TextView)findViewById(R.id.btn_more);
        btn_scan=(FloatingActionButton)findViewById(R.id.btn_scan) ;

        txt_grade.setOnClickListener(MainViewStudentActivity.this);
        txt_teacher.setOnClickListener(MainViewStudentActivity.this);
        txt_more.setOnClickListener(MainViewStudentActivity.this);
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
                Intent intent =new Intent(MainViewStudentActivity.this, com.zl.tesseract.scanner.ScannerActivity.class);
                startActivity(intent);
            }
        });

    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_grade.setSelected(false);
        txt_teacher.setSelected(false);
        txt_more.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.s_grade:
                setSelected();
                txt_grade.setSelected(true);//选中我的成绩
                txt_toolbar.setText("我的成绩");
                if(fg1 == null){
                    fg1 = new MyGradeFragment();
                    fTransaction.add(R.id.fg_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;

            case R.id.s_teacher://选中老师/家长
                setSelected();
                txt_teacher.setSelected(true);
                txt_toolbar.setText("老师/家长");
                if(fg2 == null){
                    fg2= new MyTPFragment();
                    fTransaction.add(R.id.fg_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;

            case R.id.s_more://选中更多
                setSelected();
                txt_more.setSelected(true);
                txt_toolbar.setText("更多");
                if(fg3 == null){
                    fg3= new StudentMoreFragment();
                    fTransaction.add(R.id.fg_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
//右上角更多
            case R.id.btn_more_add_user:
                Intent addUser=new Intent(MainViewStudentActivity.this,SearchStudentToAddActivity.class);
                startActivity(addUser);
                break;
            case R.id.btn_more_out_login:
                Intent outLogin=new Intent(MainViewStudentActivity.this,LoginActivity.class);
                startActivity(outLogin);
                Toast.makeText(MainViewStudentActivity.this,"成功退出登录",Toast.LENGTH_LONG).show();
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
            View popView = getLayoutInflater().inflate(R.layout.btn_more_list_student, null);
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
