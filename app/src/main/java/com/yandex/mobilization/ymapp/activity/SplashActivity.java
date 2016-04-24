package com.yandex.mobilization.ymapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yandex.mobilization.ymapp.R;

import java.io.File;
import java.io.IOException;


public class SplashActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private String splashload;
    private String splashtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashlayout);


        setActivityBackgroundColor(getResources().getColor(R.color.cardview_light_background));

        splashload = getResources().getString(R.string.splashload);
        splashtitle = getResources().getString(R.string.splashtitle);

        pd = new ProgressDialog(this);
        pd.setTitle(splashtitle);
        pd.setMessage(splashload);
        pd.show();
        pd.setCanceledOnTouchOutside(false);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);  //Delay of 3 seconds
                } catch (Exception e) {
                }
                handler.sendEmptyMessage(0);
            }
        };
        t.start();


    }



    private Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();


            Intent i = new Intent(SplashActivity.this,
                    MainActivity.class);
            startActivity(i);
            finish();

        }
    };

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }



}
