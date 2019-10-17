package cn.admob.mopub_mytest.adactivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.admob.mopub_mytest.R;

/**
 * @Description:
 * @Author: zhangsw
 * @CreateDate: 2019-10-14 13:45
 */
public class IntersitialADActivity extends AppCompatActivity {

    private static final String TAG = "Mopub>>Intersitial";

    private static final String intersitialADId = "24534e1901884e398f1253216226017e";
    private TextView adIdTv, loadTv, playTv;

    private boolean isLoadADSuccess = false;
    // 插屏广告
    private MoPubInterstitial mInterstitialAD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_intersitial);
        adIdTv = findViewById(R.id.adunitid);
        loadTv = findViewById(R.id.loadAD);
        playTv = findViewById(R.id.playAD);
        adIdTv.setText("广告ID:" + intersitialADId);

        onClickView();
    }

    private void onClickView() {
        loadTv.setOnClickListener(view -> {
            mInterstitialAD = new MoPubInterstitial(this, intersitialADId);
            mInterstitialAD.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                @Override
                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                    isLoadADSuccess = true;
                    Log.d(TAG, "onInterstitialLoaded: 广告加载成功");
                    Toast.makeText(IntersitialADActivity.this, "广告加载成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                    Log.d(TAG, "onInterstitialFailed: 广告加载失败:" + errorCode);
                }

                @Override
                public void onInterstitialShown(MoPubInterstitial interstitial) {
                    Log.d(TAG, "onInterstitialShown: 广告展示");
                }

                @Override
                public void onInterstitialClicked(MoPubInterstitial interstitial) {
                    Log.d(TAG, "onInterstitialClicked: 广告被点击");
                }

                @Override
                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                    Log.d(TAG, "onInterstitialDismissed: 广告被关闭");
                }
            });

            mInterstitialAD.load();
        });

        playTv.setOnClickListener(view -> {
            if (isLoadADSuccess && mInterstitialAD.isReady()) {
                mInterstitialAD.show();
            } else {
                Toast.makeText(IntersitialADActivity.this, "请先加载广告后再播放", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mInterstitialAD != null) {
            mInterstitialAD.destroy();
        }
    }


    /*****************************某些SDK需要监听activity的生命周期******************************/
    @Override
    protected void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }
}
