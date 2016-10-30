package com.alick.ntp_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btn_startCalibration;
    private Button btn_stopCalibration;
    private ListView lv_time;
    private TimeCalibrateHelper timeCalibrateHelper;

    private List<TimeBean> timeBeanList;
    private TimeAdapter timeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_startCalibration= (Button) findViewById(R.id.btn_startCalibration);
        btn_stopCalibration= (Button) findViewById(R.id.btn_stopCalibration);
        lv_time= (ListView) findViewById(R.id.lv_time);

        btn_startCalibration.setOnClickListener(this);
        btn_stopCalibration.setOnClickListener(this);

        timeCalibrateHelper=new TimeCalibrateHelper();

        timeBeanList=new ArrayList<>();
        timeAdapter=new TimeAdapter(timeBeanList);
        lv_time.setAdapter(timeAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_startCalibration:
                Toast.makeText(this,"开始校准",Toast.LENGTH_SHORT).show();
                timeCalibrateHelper.startCalibrateTime(new TimeCalibrateHelper.ICalibrateListener() {
                    @Override
                    public void onCalibrateSuccess(TimeBean timeBean) {
                        timeBeanList.add(timeBean);
                        timeAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCalibrateFail(String serverHost) {
                        timeBeanList.add(new TimeBean(serverHost,0,0));
                        timeAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.btn_stopCalibration:
                Toast.makeText(this,"停止校准",Toast.LENGTH_SHORT).show();
                timeCalibrateHelper.stopCalibrateTime();
                break;

        }
    }
}
