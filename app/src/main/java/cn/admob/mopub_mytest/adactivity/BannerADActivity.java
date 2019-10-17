package cn.admob.mopub_mytest.adactivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mopub.common.MoPub;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.admob.mopub_mytest.R;

/**
 * @Description:
 * @Author: zhangsw
 * @CreateDate: 2019-10-14 13:44
 */
public class BannerADActivity extends AppCompatActivity {

    private static final String TAG = "Mopub>>BannerAD";

    private static final String bannerADId = "b195f8dd8ded45fe847ad89ed1d016da";

    private TextView adIdTv, loadTv;
    private RelativeLayout flContainer;
    private MoPubView mopubADView;
//    private MoPubView moPubView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_banner);
        adIdTv = findViewById(R.id.adunitid);
        loadTv = findViewById(R.id.loadAD);
        flContainer = findViewById(R.id.flContainer);
        mopubADView = findViewById(R.id.mopubADView);
        adIdTv.setText("广告ID:" + bannerADId);

        onClickView();
    }

    private void onClickView() {

        loadTv.setOnClickListener(view -> {
//            moPubView = new MoPubView(BannerADActivity.this);
            mopubADView.setAdUnitId(bannerADId);
//            mopubADView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50);
            // 设置自动刷新
            mopubADView.setAutorefreshEnabled(true);
            mopubADView.setBannerAdListener(new MoPubView.BannerAdListener() {
                @Override
                public void onBannerLoaded(MoPubView banner) {
                    Log.d(TAG, "onBannerLoaded: 加载完成");
                    if (flContainer.getChildCount() > 0) {
                        flContainer.removeAllViews();
                    }
//                    flContainer.addView(banner);
                }

                @Override
                public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                    Log.d(TAG, "onBannerFailed: 加载失败,错误码:" + errorCode);
                }

                @Override
                public void onBannerClicked(MoPubView banner) {
                    Log.d(TAG, "onBannerClicked: 广告点击");
                }

                @Override
                public void onBannerExpanded(MoPubView banner) {
                    Log.d(TAG, "onBannerExpanded: 广告展示");
                }

                @Override
                public void onBannerCollapsed(MoPubView banner) {
                    Log.d(TAG, "onBannerCollapsed: 广告关闭");
                }
            });
            // 加载广告
            mopubADView.loadAd();
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mopubADView != null) {
            mopubADView.destroy();
        }

        if (flContainer.getChildCount() > 0) {
            flContainer.removeAllViews();
        }
    }
}
