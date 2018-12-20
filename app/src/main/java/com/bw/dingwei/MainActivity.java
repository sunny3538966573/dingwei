package com.bw.dingwei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;

public class MainActivity extends AppCompatActivity {

    private TextView text_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLocation();
        text_map = findViewById(R.id.text_map);
        text_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient.startLocation();//可以写在点击事件中
                initLocation();
            }
        });
    }
    public AMapLocationClient mLocationClient = null;

    private void initLocation(){
        //声明AMapLocationClient类对象
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//异步获取定位结果
        AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //解析定位结果
                        text_map.setText(amapLocation.getAddress());
                        mLocationClient.stopLocation();
                    }
                }
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //启动定位

    }

}