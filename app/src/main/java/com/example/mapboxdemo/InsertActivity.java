package com.example.mapboxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    public static final int LOCATION_CODE = 301;
    private LocationManager locationManager;
    private String locationProvider = null;
    private EditText longitude;
    private EditText latitude;
    private boolean initData=false;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去除标签栏
        setContentView(R.layout.activity_insert);
        longitude=findViewById(R.id.longitude);
        latitude=findViewById(R.id.latitude);
        button=findViewById(R.id.button);


                final CommonDialog dialog = new CommonDialog(InsertActivity.this);
                dialog.setMessage("是否使用当前位置信息？")
//                        .setImageResId(R.drawable.locate)
                        .setTitle("系统提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        getLongitudeLatitude();
                        dialog.dismiss();
                        Toast.makeText(InsertActivity.this,"xxxx",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegativeClick() {
                        dialog.dismiss();
                        Toast.makeText(InsertActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
    public void getLongitudeLatitude()
    {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //添加权限检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //设置每一秒获取一次location信息
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,      //GPS定位提供者
                1000,       //更新数据时间为1秒
                1,      //位置间隔为1米
                //位置监听器
                new LocationListener() {  //GPS定位信息发生改变时触发，用于更新位置信息

                    @Override
                    public void onLocationChanged(Location location) {
                        //GPS信息发生改变时，更新位置
                        locationUpdates(location);
                    }

                    @Override
                    //位置状态发生改变时触发
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    //定位提供者启动时触发
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    //定位提供者关闭时触发
                    public void onProviderDisabled(String provider) {
                    }
                });
        //从GPS获取最新的定位信息
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationUpdates(location);    //将最新的定位信息传递给创建的locationUpdates()方法中
    }

    public void locationUpdates(Location location) {  //获取指定的查询信息
        //如果location不为空时
        if (location != null) {
//            StringBuilder stringBuilder = new StringBuilder();        //使用StringBuilder保存数据
//            //获取经度、纬度、等属性值
//            stringBuilder.append("您的位置信息：\n");
//            stringBuilder.append("经度：");
//            stringBuilder.append(location.getLongitude());
//            stringBuilder.append("\n纬度：");
//            stringBuilder.append(location.getLatitude());
//            stringBuilder.append("\n精确度：");
//            stringBuilder.append(location.getAccuracy());
//            stringBuilder.append("\n高度：");
//            stringBuilder.append(location.getAltitude());
//            stringBuilder.append("\n方向：");
//            stringBuilder.append(location.getBearing());
//            stringBuilder.append("\n速度：");
//            stringBuilder.append(location.getSpeed());
//            stringBuilder.append("\n时间：");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH mm ss");    //设置日期时间格式
//            stringBuilder.append(dateFormat.format(new Date(location.getTime())));
//            text.setText(stringBuilder);            //显示获取的信息
            longitude.setText(location.getLongitude()+"");
            latitude.setText(location.getLatitude()+"");
        } else {
            //否则输出空信息
            longitude.setText("没有获取到GPS信息");
            latitude.setText("没有获取到GPS信息");
        }
    }
}