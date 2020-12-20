package com.umeng.soexample.ui.easemob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.umeng.soexample.R;
import com.umeng.soexample.utils.GlideEngine;
import com.umeng.soexample.utils.SpUtils;

import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    String bucketName = "user9";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";

    String key = "LTAI4G4aVKW5axoFkrB9XajF";  //appkey
    String secret = "XKg56Ef6PNGdQjjvXoVn6sa1R1n9E9";  //密码


    String username;
    ImageView imghead;

    private OSS ossClient;
    /**
     * 修改完成
     */
    private Button mBtnCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_perlor );
        initView();

        initData();
        initOss();

    }

    private void initView() {
        imghead = findViewById( R.id.replace_header );
        mBtnCallBack = (Button) findViewById( R.id.btn_callBack );

        imghead.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto();
            }
        } );

        mBtnCallBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( UserDetailActivity.this,EaseMobActivity.class ) );
                finish();
            }
        } );


    }

    private void initOss() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider( key, secret, "" );
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout( 15 * 1000 ); // 连接超时，默认15秒。
        conf.setSocketTimeout( 15 * 1000 ); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest( 5 ); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry( 2 ); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient( getApplicationContext(), ossPoint, credentialProvider );
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra( "username" )) {
            username = intent.getStringExtra( "username" );
        }

        String head = SpUtils.getInstance().getString( username );
        if (!TextUtils.isEmpty( head )) {
            Glide.with( imghead ).load( head ).into( imghead );
        }
    }

    private void openPhoto() {
        PictureSelector.create( this )
                .openGallery( PictureMimeType.ofImage() )
                .loadImageEngine( GlideEngine.createGlideEngine() ) // Please refer to the Demo GlideEngine.java
                .maxSelectNum( 1 )
                .imageSpanCount( 4 )
                .selectionMode( PictureConfig.MULTIPLE )
                .forResult( PictureConfig.CHOOSE_REQUEST );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult( data );
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                for (LocalMedia item : selectList) {
                    uploadHead( item.getPath() );
                    //保存
                    SpUtils.getInstance().setValue( username, item.getPath() );
                }
                break;
            default:
                break;
        }
    }

    /**
     * oss上传
     *
     * @param path
     */
    private void uploadHead(String path) {
        Glide.with( imghead ).load( path ).into( imghead );
        String fileName = path.substring( path.lastIndexOf( "/" ) + 1, path.length() );
        PutObjectRequest put = new PutObjectRequest( bucketName, fileName, path );
        put.setProgressCallback( new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.i( "oss_upload", currentSize + "/" + totalSize );
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if (currentSize == totalSize) {
                    //完成
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.i( "HeadUrl", headUrl );
                    //request.getUploadFilePath()
                }

            }
        } );
        OSSAsyncTask task = ossClient.asyncPutObject( put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d( "PutObject", "UploadSuccess" );
                Log.d( "ETag", result.getETag() );
                Log.d( "RequestId", result.getRequestId() );
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL( request.getBucketName(), request.getObjectKey() );
                //调用服务器接口 把url上传到服务器的接口
                SpUtils.getInstance().setValue( username, url );
                updateHead( url );
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e( "ErrorCode", serviceException.getErrorCode() );
                    Log.e( "RequestId", serviceException.getRequestId() );
                    Log.e( "HostId", serviceException.getHostId() );
                    Log.e( "RawMessage", serviceException.getRawMessage() );
                }
            }
        } );
    }

    private void updateHead(String url) {
        //赋值到组件上
        imghead.post( new Runnable() {
            @Override
            public void run() {
                Glide.with( imghead ).load( url ).into( imghead );
            }
        } );
    }
}