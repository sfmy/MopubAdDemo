package cn.admob.mopub_mytest.adactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mopub.nativeads.AdapterHelper;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.admob.mopub_mytest.R;

/**
 * @Description:
 * @Author: zhangsw
 * @CreateDate: 2019-10-14 13:45
 */
public class NativeADActivity extends AppCompatActivity {

    private static final String TAG = "Mopub>>Native";

    private static final String nativeADId = "11a17b188668469fb0412708c3d16813";

    private TextView adIdTv, loadTv;
    private RelativeLayout flContainer;

    private MoPubNative moPubNative;
    private AdapterHelper adapterHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_native);
        adIdTv = findViewById(R.id.adunitid);
        loadTv = findViewById(R.id.loadAD);
        flContainer = findViewById(R.id.flContainer);
        adIdTv.setText("广告ID:" + nativeADId);

        onClickView();
    }

    private void onClickView() {

        loadTv.setOnClickListener(view -> {
            adapterHelper = new AdapterHelper(this, 0, 3); // When standalone, any range will be fine.

            moPubNative = new MoPubNative(this, nativeADId, new MoPubNative.MoPubNativeNetworkListener() {
                @Override
                public void onNativeLoad(NativeAd nativeAd) {
                    Log.d(TAG, "onNativeLoad: 广告加载成功");

                    if (nativeAd!=null){
                        nativeAd.setMoPubNativeEventListener(new NativeAd.MoPubNativeEventListener() {
                            @Override
                            public void onImpression(View view) {
                                Log.d(TAG, "Native ad recorded an impression. 广告展示");
                            }

                            @Override
                            public void onClick(View view) {
                                Log.d(TAG, "onClick: 广告点击回调");
                            }
                        });
                    }

                    // Retrieve the pre-built ad view that AdapterHelper prepared for us.
                    View adView = adapterHelper.getAdView(null, null, nativeAd, new ViewBinder.Builder(0).build());

                    flContainer.addView(adView);
                    TextView adTitle = (TextView)adView.findViewById(R.id.native_title);
                    TextView adTitle2 = (TextView)adView.findViewById(R.id.native_text);
                    Log.d(TAG, "onNativeLoad: title:" + adTitle.getText().toString());
                    Log.d(TAG, "onNativeLoad: text:" + adTitle2.getText().toString());

                }

                @Override
                public void onNativeFail(NativeErrorCode errorCode) {
                    Log.d(TAG, "onNativeFail: 广告加载失败");
                }
            });

            // 绑定视图View
//            ViewBinder viewBinder = new ViewBinder.Builder(R.layout.native_ad_list_item)
            ViewBinder viewBinder = new ViewBinder.Builder(R.layout.leftimage_ad)
                    .mainImageId(R.id.native_main_image)
//                    .iconImageId(R.id.native_icon_image)
                    .titleId(R.id.native_title)
                    .textId(R.id.native_text)
//                    .privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                    .build();

            MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(viewBinder);

            if (moPubNative != null) {
                moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);
                // 从服务器请求广告并异步下载关联的图像
                moPubNative.makeRequest();
            }

        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (moPubNative != null) {
            moPubNative.destroy();
        }
    }
}
