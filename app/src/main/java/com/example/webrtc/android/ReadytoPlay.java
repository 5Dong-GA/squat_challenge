package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReadytoPlay extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 5010; // 5.01초 후에 화면 전환
    private static CameraPreview surfaceView;
    public static ReadytoPlay getInstance;
    private SurfaceHolder holder;
    private static Button camera_preview_button;
    private static Camera mCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readytoplay);

        TextView count1, count2, count3, count4, count5;

        count1 = findViewById(R.id.count1);
        count2 = findViewById(R.id.count2);
        count3 = findViewById(R.id.count3);
        count4 = findViewById(R.id.count4);
        count5 = findViewById(R.id.count5);

        count1.animate().alpha(0).setDuration(10).setStartDelay(1000);
        count2.animate().alpha(1).setDuration(10).setStartDelay(1000);
        count2.animate().alpha(0).setDuration(10).setStartDelay(2000);
        count3.animate().alpha(1).setDuration(10).setStartDelay(2000);
        count3.animate().alpha(0).setDuration(10).setStartDelay(3000);
        count4.animate().alpha(1).setDuration(10).setStartDelay(3000);
        count4.animate().alpha(0).setDuration(10).setStartDelay(4000);
        count5.animate().alpha(1).setDuration(10).setStartDelay(4000);
        count5.animate().alpha(0).setDuration(10).setStartDelay(5000);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(ReadytoPlay.this, solo_speed_play.class);
            startActivity(intent);
        }, SPLASH_SCREEN);
    }

    public static Camera getCamera() {
        return mCamera;
    }


    private void setInit() {
        getInstance = this;

        int cameraID = Camera.CameraInfo.CAMERA_FACING_FRONT;
        // 카메라 객체를 R.layout.activity_main의 레이아웃에 선언한 SurfaceView에서 먼저 정의해야 함으로 setContentView 보다 먼저 정의한다.
        mCamera = Camera.open(cameraID);

        setContentView(R.layout.activity_readytoplay);

        // SurfaceView를 상속받은 레이아웃을 정의한다.
        surfaceView = (CameraPreview) findViewById(R.id.preview);
        //리스너 설정
        //mCamera.setPreviewCallback(this::onPreviewFrame);
        // SurfaceView 정의 - holder와 Callback을 정의한다.
        holder = surfaceView.getHolder();
        holder.addCallback(surfaceView);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
}