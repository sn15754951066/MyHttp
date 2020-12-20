package com.umeng.soexample.ui.map;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.mapapi.search.district.DistrictSearch;
import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.mapapi.search.district.OnGetDistricSearchResultListener;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mapapi.clusterutil.clustering.Cluster;
import mapapi.clusterutil.clustering.ClusterItem;
import mapapi.clusterutil.clustering.ClusterManager;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.mapView)
    MapView mMapView;

    //百度地图的数据操作
    BaiduMap baiduMap;
    //百度地图定位的类
    LocationClient locationClient;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private ArrayList<PoiInfo> poiInfos;
    private MapSearchAdapter mapSearchAdapter;
    private PoiSearch poiSearch;
    private String address;
    private InfoWindow mInfoWindow;
    private ClusterManager<MyItem> myItemClusterManager;

    private static final int MIN_CLUSTER_SIZE = 1;
    //初始化点聚合管理类
    private ClusterManager<MyItem> mClusterManager;
    private DistrictSearch mDistrictSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_map );
        ButterKnife.bind( this );
        initMap();
        initLocation();

        initPoi();
        initView();
    }

    private void initView() {
        btnSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //搜索
                search();
                districtSearch();
                //聚合
                //cluster();
                //make();


            }
        } );
    }


    private void initMap() {
        baiduMap = mMapView.getMap();
        baiduMap.setMyLocationEnabled( true );
        //设置为普通类型的地图
        baiduMap.setMapType( BaiduMap.MAP_TYPE_NORMAL );

//        //自定义地图
//        baiduMap.setTrafficEnabled(true);
//        baiduMap.setCustomTrafficColor("#ffba0101", "#fff33131", "#ffff9e19", "#00000000");
//  对地图状态做更新，否则可能不会触发渲染，造成样式定义无法立即生效。
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo( 13 );
        baiduMap.animateMapStatus( u );
    }

    private void make() {
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        baiduMap.setOnMapStatusChangeListener( mClusterManager );
        // 设置maker点击时的响应
        baiduMap.setOnMarkerClickListener( mClusterManager );

        mClusterManager.setOnClusterClickListener( new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                Toast.makeText( MapActivity.this,
                        "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT ).show();

                return false;
            }
        } );
        mClusterManager.setOnClusterItemClickListener( new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem item) {

                Toast.makeText( MapActivity.this,
                        "点击单个Item", Toast.LENGTH_SHORT ).show();

                return false;
            }
        } );
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        //定位初始化
        locationClient = new LocationClient( this );

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps( true ); // 打开gps
        option.setCoorType( "bd09ll" ); // 设置坐标类型
        option.setScanSpan( 1000 );

        //设置locationClientOption
        locationClient.setLocOption( option );

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener( myLocationListener );
        //开启地图定位图层
        locationClient.start();
    }


    /**
     * 地图定位的监听
     */
    private class MyLocationListener extends BDAbstractLocationListener {


        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //mapView 销毁后不在处理新接收的位置
            if (bdLocation == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy( bdLocation.getRadius() )
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction( bdLocation.getDirection() ).latitude( bdLocation.getLatitude() )
                    .longitude( bdLocation.getLongitude() ).build();
            baiduMap.setMyLocationData( locData );
        }
    }

    /*******************检索*********************/

    private void initPoi() {
        poiInfos = new ArrayList<>();
        rvMain.setLayoutManager( new LinearLayoutManager( this ) );
        mapSearchAdapter = new MapSearchAdapter( poiInfos, this );
        rvMain.setAdapter( mapSearchAdapter );

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener( poiSearchResultListener );

        //接口回调
        mapSearchAdapter.setClick( new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int positions) {

                PoiInfo poiInfo = poiInfos.get( positions );
                address = poiInfo.address;
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng( poiInfo.location );
                baiduMap.setMapStatus( mapStatusUpdate );

                //自定义圆
                drawCircle( poiInfo.location.latitude, poiInfo.location.longitude );
                addMark( poiInfo.location.latitude, poiInfo.location.longitude );
                textOptions( poiInfo.location.latitude, poiInfo.location.longitude );
                infoWindow( poiInfo.location.latitude, poiInfo.location.longitude );

            }
        } );
    }

    /**
     * 搜索监听
     */

    private void search() {
        String search = etSearch.getText().toString();
        if (!TextUtils.isEmpty( search )) {
            PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption();
            poiCitySearchOption.city( "北京" );
            poiCitySearchOption.keyword( search );
            poiSearch.searchInCity( poiCitySearchOption );
        }


    }

    /**
     * 搜索的监听
     */
    OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.i( TAG, "onGetPoiResult" );
            //先清空 在刷新
            poiInfos.clear();
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                poiInfos.addAll( poiResult.getAllPoi() );
                mapSearchAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.i( TAG, "onGetPoiDetailResult" );
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i( TAG, "onGetPoiDetailResult" );
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i( TAG, "onGetPoiIndoorResult" );
        }
    };


    //创建行政区边界数据检索实例
    private void districtSearch() {
        mDistrictSearch = DistrictSearch.newInstance();
        //设置行政区边界数据检索监听器
        mDistrictSearch.setOnDistrictSearchListener( listener );

        String search = etSearch.getText().toString();
        //设置DistrictSearchOption，发起检索
        mDistrictSearch.searchDistrict( new DistrictSearchOption()
                .cityName( "北京市" )
                .districtName( search) );

    }

    //创建行政区边界数据检索监听器
    OnGetDistricSearchResultListener listener = new OnGetDistricSearchResultListener() {
        @Override
        public void onGetDistrictResult(DistrictResult districtResult) {
            if (null == districtResult) {
                return;
            }
            baiduMap.clear();
            //获取边界坐标点，并展示
            if (districtResult.error == SearchResult.ERRORNO.NO_ERROR) {
                List<List<LatLng>> polyLines = districtResult.getPolylines();
                if (polyLines == null) {
                    return;
                }
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (List<LatLng> polyline : polyLines) {
                    OverlayOptions ooPolyline11 = new PolylineOptions().width( 10 )
                            .points( polyline ).dottedLine( true ).color( Color.BLUE );
                    baiduMap.addOverlay( ooPolyline11 );
                    OverlayOptions ooPolygon = new PolygonOptions().points( polyline )
                            .stroke( new Stroke( 5, 0xAA00FF88 ) ).fillColor( 0xAAFFFF00 );
                    baiduMap.addOverlay( ooPolygon );
                    for (LatLng latLng : polyline) {
                        builder.include( latLng );
                    }
                }
                baiduMap.setMapStatus( MapStatusUpdateFactory
                        .newLatLngBounds( builder.build() ) );

            }
        }

    };


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mDistrictSearch.destroy();
    }

    private void drawCircle(double lat, double gt) {
        //设置圆心位置
        LatLng center = new LatLng( lat, gt );
        //设置圆对象
        CircleOptions circleOptions = new CircleOptions().center( center )
                .radius( 200 )
                .fillColor( 0x50ff0000 )
                .stroke( new Stroke( 2, 0xff000000 ) );

        baiduMap.clear();
        baiduMap.addOverlay( circleOptions );

    }

    //定位图标
    private void addMark(double lat, double gt) {
        LatLng point = new LatLng( lat, gt );
        //构建Mark图标
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                .fromResource( R.drawable.notation );

        //构建MarkerOption 用于在地图上添加Marker
        MarkerOptions icon = new MarkerOptions()
                .position( point )
                .icon( bitmapDescriptor );
        //设置掉下动画
        icon.animateType( MarkerOptions.MarkerAnimateType.drop );

        //在地图上添加Marker，并显示
        baiduMap.addOverlay( icon );
        String s = baiduMap.toString();
        Log.e( "111222", "addMark: " + s );
    }

    //文字覆盖物
    private void textOptions(double lat, double gt) {
        LatLng point = new LatLng( lat, gt );
        //构建TextOptions对象
        OverlayOptions mTextOptions = new TextOptions()
                .text( address ) //文字内容
                .bgColor( 0xAAFFFF00 ) //背景色
                .fontSize( 24 ) //字号
                .fontColor( 0xFFFF00FF ) //文字颜色
                .rotate( 0 ) //旋转角度
                .position( point );

        //在地图上显示文字覆盖物
        baiduMap.addOverlay( mTextOptions );
    }

    //添加信息窗（弹窗覆盖物InfoWindow）
    private void infoWindow(double lat, double gt) {

        LatLng point = new LatLng( lat, gt );
        //用来构造InfoWindow的Button
        Button button = new Button( getApplicationContext() );
        button.setBackgroundResource( R.drawable.popup );
        button.setText( address );

        //构造InfoWindow
        //point 描述的位置点
        //-100 InfoWindow相对于point在y轴的偏移量
        mInfoWindow = new InfoWindow( button, point, -30 );

        //使InfoWindow生效
        baiduMap.showInfoWindow( mInfoWindow );

    }


    //点聚合
    public void cluster() {


        // 定义点聚合管理类ClusterManager
        myItemClusterManager = new ClusterManager<MyItem>( this, baiduMap );

        List<MyItem> items = new ArrayList<>();
        for (int i = 0; i < poiInfos.size(); i++) {

            PoiInfo poiInfo = poiInfos.get( i );
            //获取经纬度添加地图标注
            LatLng point = new LatLng( poiInfo.location.latitude, poiInfo.location.longitude );

            MyItem myItem = new MyItem( point );
            items.add( myItem );

        }
        mClusterManager.addItems( items );//把点添加到聚合类里
    }


    /**
     * 每个Marker点，包含Marker点坐标、图标以及额外信息
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;//点

        public MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {//点图标
            return BitmapDescriptorFactory.fromResource( R.drawable.location );
        }
    }

    private static final String TAG = "MapActivity";


}