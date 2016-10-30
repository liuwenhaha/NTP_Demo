package com.alick.ntp_test;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btn_startCalibration;
    private Button btn_stopCalibration;
    private TimeCalibrateHelper timeCalibrateHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_startCalibration= (Button) findViewById(R.id.btn_startCalibration);
        btn_stopCalibration= (Button) findViewById(R.id.btn_stopCalibration);

        btn_startCalibration.setOnClickListener(this);
        btn_stopCalibration.setOnClickListener(this);

        timeCalibrateHelper=new TimeCalibrateHelper();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_startCalibration:
                timeCalibrateHelper.startCalibrateTime();
                break;
            case R.id.btn_stopCalibration:
                timeCalibrateHelper.stopCalibrateTime();
                break;

        }
    }
}
