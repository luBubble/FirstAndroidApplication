package com.zl.tesseract.scanner;
import net.sourceforge.jeval.*;
import net.sourceforge.jeval.function.*;
import net.sourceforge.jeval.function.math.*;
import net.sourceforge.jeval.function.string.*;
import net.sourceforge.jeval.operator.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.zl.tesseract.R;
import com.zl.tesseract.scanner.camera.CameraManager;
import com.zl.tesseract.scanner.decode.CaptureActivityHandler;
import com.zl.tesseract.scanner.decode.DecodeManager;
import com.zl.tesseract.scanner.decode.InactivityTimer;
import com.zl.tesseract.scanner.saveGrade.PickStudentActivity;
import com.zl.tesseract.scanner.tess.TesseractCallback;
import com.zl.tesseract.scanner.tess.TesseractThread;
import com.zl.tesseract.scanner.utils.Tools;
import com.zl.tesseract.scanner.view.ImageDialog;
import com.zl.tesseract.scanner.view.ScannerFinderView;


//tts库
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.DecimalFormat;
import java.util.Locale;

import java.io.IOException;

import sql.SQLiteHelper;

/**
 * 二维码扫描类。
 */
public class ScannerActivity extends Activity implements Callback, Camera.PictureCallback, Camera.ShutterCallback{

    private CaptureActivityHandler mCaptureActivityHandler;
    private boolean mHasSurface;
    private InactivityTimer mInactivityTimer;
    private ScannerFinderView mQrCodeFinderView;
    private SurfaceView mSurfaceView;
    private ViewStub mSurfaceViewStub;
    private DecodeManager mDecodeManager = new DecodeManager();
    private Switch switch1;
    private Button bt;
    private SQLiteHelper db;
    private ImageView scanImg;
    private TextView scanImgText;

    private int teacherId;
    public int grade;
    public String wrong;

    private ProgressDialog progressDialog;
    private Bitmap bmp;
//tts变量
    TextToSpeech tts;
    int result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        teacherId=getIntent().getExtras().getInt("teacherId");
        db = new SQLiteHelper(this, "person.db", null, 1);
        setContentView(R.layout.activity_scanner);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //初始化成功的话，设置语音，这里我将它设置为中文
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.CHINA);
                }
            }
        });
        initView();
        initData();

            }
//播报
    public void broadcast(View view) {
        tts.speak("请勿偏离轨道，好好开车", TextToSpeech.QUEUE_ADD, null);
        Log.e("111", result + "");
    }

//    public void onInit(int status) {
//        if (status == TextToSpeech.SUCCESS) {
//            tts.setLanguage(Locale.CHINA);
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if (tts != null) {
            tts.shutdown();
        }
    }
//---------------------------------------------------------------------

    private void initView() {
        mQrCodeFinderView = (ScannerFinderView) findViewById(R.id.qr_code_view_finder);
        mSurfaceViewStub = (ViewStub) findViewById(R.id.qr_code_view_stub);
        switch1 = (Switch) findViewById(R.id.switch1);
        mHasSurface = false;

        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setEnabled(false);
                buildProgressDialog();
                CameraManager.get().takeShot(ScannerActivity.this, ScannerActivity.this, ScannerActivity.this);
            }
        });

        Switch switch2 = (Switch) findViewById(R.id.switch2);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CameraManager.get().setFlashLight(isChecked);
            }
        });
    }

    public Rect getCropRect() {
        return mQrCodeFinderView.getRect();
    }

    public boolean isQRCode() {
        return switch1.isChecked();
    }

    private void initData() {
        mInactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CameraManager.init();
        initCamera();
    }

    private void initCamera() {
        if (null == mSurfaceView) {
            mSurfaceViewStub.setLayoutResource(R.layout.layout_surface_view);
            mSurfaceView = (SurfaceView) mSurfaceViewStub.inflate();
        }
        SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        if (mHasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCaptureActivityHandler != null) {
            try {
                mCaptureActivityHandler.quitSynchronously();
                mCaptureActivityHandler = null;
                mHasSurface = false;
                if (null != mSurfaceView) {
                    mSurfaceView.getHolder().removeCallback(this);
                }
                CameraManager.get().closeDriver();
            } catch (Exception e) {
                // 关闭摄像头失败的情况下,最好退出该Activity,否则下次初始化的时候会显示摄像头已占用.
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (null != mInactivityTimer) {
            mInactivityTimer.shutdown();
        }
        super.onDestroy();
    }

    /**
     * Handler scan result
     *
     * @param result
     */
    public void handleDecode(Result result) {
        mInactivityTimer.onActivity();
        if (null == result) {
            mDecodeManager.showCouldNotReadQrCodeFromScanner(this, new DecodeManager.OnRefreshCameraListener() {
                @Override
                public void refresh() {
                    restartPreview();
                }
            });
        } else {
            handleResult(result);
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            if (!CameraManager.get().openDriver(surfaceHolder)) {
                return;
            }
        } catch (IOException e) {
            // 基本不会出现相机不存在的情况
            Toast.makeText(this, getString(R.string.camera_not_found), Toast.LENGTH_SHORT).show();
            finish();
            return;
        } catch (RuntimeException re) {
            re.printStackTrace();
            return;
        }
        mQrCodeFinderView.setVisibility(View.VISIBLE);
        findViewById(R.id.qr_code_view_background).setVisibility(View.GONE);
        if (mCaptureActivityHandler == null) {
            mCaptureActivityHandler = new CaptureActivityHandler(this);
        }
    }

    public void restartPreview() {
        if (null != mCaptureActivityHandler) {
            try {
                mCaptureActivityHandler.restartPreviewAndDecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!mHasSurface) {
            mHasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHasSurface = false;
    }

    public Handler getCaptureActivityHandler() {
        return mCaptureActivityHandler;
    }

    private void handleResult(Result result) {
        if (TextUtils.isEmpty(result.getText())) {
            mDecodeManager.showCouldNotReadQrCodeFromScanner(this, new DecodeManager.OnRefreshCameraListener() {
                @Override
                public void refresh() {
                    restartPreview();
                }
            });
        } else {
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200L);
            if (switch1.isChecked()) {
                qrSucceed(result.getText());
            } else {
                phoneSucceed(result.getText(), result.getBitmap());
            }
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        if (data == null) {
            return;
        }
        mCaptureActivityHandler.onPause();
        bmp = null;
        bmp = Tools.getFocusedBitmap(this, camera, data, getCropRect());

        TesseractThread mTesseractThread = new TesseractThread(bmp, new TesseractCallback() {

            @Override
            public void succeed(String result) {
                Message message = Message.obtain();
                message.what = 0;
                message.obj = result;
                mHandler.sendMessage(message);
            }

            @Override
            public void fail() {
                Message message = Message.obtain();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        });

        Thread thread = new Thread(mTesseractThread);
        thread.start();
    }

    @Override
    public void onShutter() {}

    private void qrSucceed(String result){
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.notification)
                .setMessage(result)
                .setPositiveButton(R.string.positive_button_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        restartPreview();
                    }
                })
                .show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                restartPreview();
            }
        });
    }

    private void phoneSucceed (String result, Bitmap bitmap) {
        String name1=result;
        String[] suanshi=name1.split(",");
        String shuchu="";
        grade=0;
        wrong="";
        int account=0;
        int i=0;
        while(i<suanshi.length) {
            String name = suanshi[i];
            String[] shuzi = name.split("=");
            String expr = shuzi[0];
            String expr1 = shuzi[1];
            Evaluator evaluator = new Evaluator();
            String daan = null;
            String daan1 = null;
            try {
               // broadcast();
                daan1=evaluator.evaluate(expr1);
                daan = evaluator.evaluate(expr);
                if(daan.equals(daan1))
                {
                    shuchu=shuchu+"正确 ";
                    account+=1;
                    grade+=50;
                }
                else
                {
                    shuchu=shuchu+"错误 ";
                    account+=1;
                    if (!wrong.equals(""))
                    {
                        wrong+=",";
                        wrong+=String.valueOf(account);
                    }
                    else
                    {
                        wrong+=String.valueOf(account);
                    }
                }

//            String[]  B=daan.split(".");
//            daan=B[0];
            } catch (EvaluationException ee) {
                Toast.makeText(getApplicationContext(), "表达式错误，请检查", Toast.LENGTH_SHORT).show();
            }
            i++;
        }
        View resultView= LayoutInflater.from(ScannerActivity.this).inflate(R.layout.two_button, null);
        scanImg=(ImageView) resultView.findViewById(R.id.scan_result);
        scanImgText=(TextView)resultView.findViewById(R.id.scan_result_text);
        scanImg.setImageBitmap(bitmap);
        scanImgText.setText(TextUtils.isEmpty(result) ? "未识别到" : shuchu);
        AlertDialog dialog = new AlertDialog.Builder(ScannerActivity.this)
                .setTitle("扫描结果")//设置对话框的标题
                .setView(resultView)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle gradeMsg=new Bundle();
                        gradeMsg.putInt("teacherId",teacherId);
                        gradeMsg.putInt("grade",grade);
                        gradeMsg.putString("wrong",wrong);
                        Intent pickStudent=new Intent(ScannerActivity.this,PickStudentActivity.class);
                        pickStudent.putExtras(gradeMsg);//传递成绩信息
                        startActivity(pickStudent);
                    }
                })
                .create();
        //ImageDialog dialog = new ImageDialog(this);
        //dialog.addBitmap(bitmap);
        //dialog.addTitle(TextUtils.isEmpty(result) ? "未识别到" : shuchu);

//识别手机号
       // System.out.println("结果"+result);
        //System.out.println(shuzi[1].equals(daan));
        //shuzi[1]=shuzi[1]+".0";
//        if(shuzi[1].equals(daan))
//        {
//            dialog.addTitle(TextUtils.isEmpty(result) ? "未识别到" : " 结果是正确");
//        }
//        else
//        {
//            dialog.addTitle(TextUtils.isEmpty(result) ? "未识别到" : " 结果是错误");
//        }
//
//        String name=db.selectPerson(result);
//
//        if(name==null)
//        {
//            name="没有查到此人";
//            dialog.addTitle(TextUtils.isEmpty(result) ? "未识别到手机号码" : result+" 的主人是"+name);
//        }
//        else
//        {
//            dialog.addTitle(TextUtils.isEmpty(result) ? "未识别到手机号码" :"电话的主人是"+name);
//        }
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                restartPreview();
            }
        });
    }



    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            bt.setEnabled(true);
            cancelProgressDialog();
            switch (msg.what){
                case 0:
                    phoneSucceed((String) msg.obj, bmp);
                    break;
                case 1:
                    Toast.makeText(ScannerActivity.this, "无法识别", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public void buildProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage("识别中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void cancelProgressDialog() {
        if (progressDialog != null){
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}