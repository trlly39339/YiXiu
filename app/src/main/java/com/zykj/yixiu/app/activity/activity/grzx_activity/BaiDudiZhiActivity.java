package com.zykj.yixiu.app.activity.activity.grzx_activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zykj.yixiu.R;
import com.zykj.yixiu.app.activity.activity_styles.MyTopBer;
import com.zykj.yixiu.app.activity.base.BaseActivity;
import com.zykj.yixiu.app.activity.bean.ChaXunAddress;
import com.zykj.yixiu.app.activity.yixiuge_utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class BaiDudiZhiActivity extends BaseActivity {


    @Bind(R.id.biaoti)
    MyTopBer biaoti;
    @Bind(R.id.et_city_name)
    EditText etCityName;
    @Bind(R.id.btOk)
    FrameLayout btOk;
    @Bind(R.id.map)
    MapView map;

    private String addrStr;
    private ChaXunAddress address=new ChaXunAddress();//用于储存百度地图所有属性

    private BaiduMap mBaiduMap;//百度地图管理对象

    LocationClient mClient;//客户端定位对象

    boolean isFirstLoc = true;//把isFirstLoc默认为ture

    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_address_management);
        ButterKnife.bind(this);
        mBaiduMap = map.getMap();
        //设置缩放级别，默认级别为12
        MapStatusUpdate mapstatusUpdate = MapStatusUpdateFactory.zoomTo(20);
        mBaiduMap.setMapStatus(mapstatusUpdate);

        init();//調用
        selectMap();
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city_name=etCityName.getText().toString();
                if (TextUtils.isEmpty(city_name)){
                    Y.t("请输出或选择地址");
                    return;
                }else {
                    //发起地理编码
                    GeoCoder geoCoder=GeoCoder.newInstance();
                    //发起地理编码
                    geoCoder.geocode(new GeoCodeOption().city(Y.USER.getCity()).address(city_name));
                    geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                        @Override
                        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                            if (geoCodeResult==null||geoCodeResult.error!= SearchResult.ERRORNO.NO_ERROR){
                                Y.t("请输入合法地址");
                            }else {
                                Y.t("地址添加成功");
                                // 更新百度地图对象
                                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(geoCodeResult.getLocation());
                                //把更新的信息告诉百度
                                mBaiduMap.animateMapStatus(msu);

                            }
                        }

                        @Override
                        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                        }
                    });
                }
            }
        });

    }
    public void selectMap(){
        //添加地址的监听事件
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override//地图点击回调
            public void onMapClick(LatLng latLng) {
                jieMa(latLng); //反地理
                setBitmap(latLng); // 添加标注
            }

            @Override//当前位置的兴趣点
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }
    //添加标注
    public void setBitmap(LatLng latLng){
        mBaiduMap.clear();
        OverlayOptions options = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        mBaiduMap.addOverlay(options);
    }
    // 地理编码
    public void jieMa(LatLng latLng) {
        GeoCoder geoCoder = GeoCoder.newInstance();  // 创建地理编码对象

        //发起反地理
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));

        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override  //  地理编码的回调
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }
            @Override      //反地理编码的回调
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                if (reverseGeoCodeResult != null) {
//                    打印一下
                    Y.t(reverseGeoCodeResult.getAddress());
//                    把地址设置到顶部输入框中
                    etCityName.setText(reverseGeoCodeResult.getAddress().toString());
//                    获取详细地址
                    ReverseGeoCodeResult.AddressComponent addressDetail=reverseGeoCodeResult.getAddressDetail();
//                    设置信息地址信息
                    address.setAddress(reverseGeoCodeResult.getAddress());
//                    设置区
                    address.setRegion(addressDetail.district);
//                    设置市
                    address.setCity_name(addressDetail.city);
//                    设置经纬度
                    LatLng ll = reverseGeoCodeResult.getLocation();
                    address.setLat(ll.latitude);
                    address.setLon(ll.longitude);

                }
            }
        });
    }
    //初始化定位
    public void init() {
        //创建定位对象
        mClient = new LocationClient(this);
        //使用LocationClientOption来定位所有信息
        LocationClientOption clientOption = new LocationClientOption();
        //百度经纬度
        clientOption.setCoorType("bd09ll");
        //是否开启GPS定位 ture为开启 false为关闭
        clientOption.setOpenGps(true);
        //定位时间，1000毫秒=1
        clientOption.setScanSpan(5000);
        // 定位成功后，返回当前的地址
        clientOption.setIsNeedAddress(true);
        //把配置的信息传给LocationClient对象
        mClient.setLocOption(clientOption);
        //定位的监听事件
        mClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (bdLocation == null || mBaiduMap == null) {
                    Y.t("定位失败");
                    return;
                }
//                吐一下
                Y.t(bdLocation.getAddrStr());
                addrStr=bdLocation.getAddrStr();
                etCityName.setText(addrStr);
                //获取BDLocation数据转换成MyLocationData
                MyLocationData myLocationData = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius()) // 设置半径
                        .latitude(bdLocation.getLatitude()) //设置经纬度
                        .longitude(bdLocation.getLongitude())//设置经纬度
                        .build();
                // 把定位的信息传给百度地图上
                mBaiduMap.setMyLocationData(myLocationData);
//                更新地图位置  获取经纬度
                latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
//                第一次的时候更新
                if (isFirstLoc) {
                    // 更新百度地图
                    MapStatus.Builder builder=new MapStatus.Builder();
                    builder.target(latLng).zoom(18.0f);
//                    MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                    //把地图移动到当前定位的地点
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                    // 不是第一次了 次判断不在执行
                    isFirstLoc = false;
                }
                com.baidu.location.Address add = bdLocation.getAddress();
//                把城市编码保存到对象中
                address.getCity_code(add.cityCode);
//                设置地址信息
                address.setAddress(add.address);
//                设置区
                address.setRegion(add.district);
//                设置市
                address.setCity_name(add.city);
//                s设置经纬度
                address.setLat(latLng.latitude);
                address.setLon(latLng.longitude);

                //在当前绘制一个标注
              setBitmap(latLng);
            }
            @Override  //返回兴趣点信息
            public void onConnectHotSpotMessage(String s, int i) {
            }
        });
    }
    //回到我的位置信息
    public void center() {
        // 更新百度地图
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        //每次更新的信息告訴百度
        mBaiduMap.animateMapStatus(msu);
    }

    @Override
    //定位调用
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if (!mClient.isStarted()) {
            mClient.start();
        }
    }

    @Override
    //选项菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("回到我的位置");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        mClient.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.toString()) {
            case "回到我的位置":
                center();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onResume() {
        super.onRestart();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }
}
