package com.example.nyadmin.jsontest2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private String splashload;
    private String splashtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashlayout);


setActivityBackgroundColor(getResources().getColor(R.color.cardview_light_background));

        splashload=getResources().getString(R.string.splashload);
        splashtitle=getResources().getString(R.string.splashtitle);

        pd=new ProgressDialog(this);
        pd.setTitle(splashtitle);
        pd.setMessage(splashload);
        pd.show();
        //pd = ProgressDialog.show(this,"Please Wait...", "Loading Application..", false, true);
        pd.setCanceledOnTouchOutside(false);
        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);  //Delay of 3 seconds
                }
                catch (Exception e) {}
                handler.sendEmptyMessage(0);
            }
        } ;
        t.start();

//Handles the thread result of the Backup being executed.


    }

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            pd.dismiss();
            //Start the Next Activity here...
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
