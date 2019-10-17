package cn.admob.mopub_mytest.adactivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.admob.mopub_mytest.BuildConfig;
import cn.admob.mopub_mytest.MyApplication;
import cn.admob.mopub_mytest.R;

import static com.mopub.common.logging.MoPubLog.LogLevel.DEBUG;
import static com.mopub.common.logging.MoPubLog.LogLevel.INFO;

/**
 * @Description:
 * @Author: zhangsw
 * @CreateDate: 2019-10-14 13:46
 */
public class RewardVideoADActivity extends AppCompatActivity {

    private static final String TAG = "Mopub>>Reward";

    private static final String rewardADId = "920b6145fb1546cf8b5cf2ac34638bb7";

    private TextView adIdTv, loadTv, playTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoPub.onCreate(this);
        setContentView(R.layout.activity_ad_reward);
        adIdTv = findViewById(R.id.adunitid);
        loadTv = findViewById(R.id.loadAD);
        playTv = findViewById(R.id.playAD);
        adIdTv.setText("广告ID:" + rewardADId);

        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder(rewardADId);


        MoPub.initializeSdk(this, sdkConfiguration.build(), new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                /* MoPub SDK initialized.
               Check if you should show the consent dialog here, and make your ad requests. */
                Log.d(TAG, "onInitializationFinished: 初始化结束");
                Toast.makeText(RewardVideoADActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();
            }
        });

//        MoPubRewardedVideos.setRewardedVideoListener(this);
        initListener();
        onClickView();
    }

    private void initListener() {
        MoPubRewardedVideos.setRewardedVideoListener(new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
                Log.d(TAG, "onRewardedVideoLoadSuccess: 激励视频缓存成功");
                Toast.makeText(RewardVideoADActivity.this,"激励视频缓存成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
                Log.d(TAG, "onRewardedVideoLoadFailure: 激励视频获取失败,错误码: " + errorCode);
                Toast.makeText(RewardVideoADActivity.this,"激励视频获取失败 错误码:" +errorCode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoStarted(@NonNull String adUnitId) {
                Log.d(TAG, "onRewardedVideoStarted: 激励视频开始播放 :" + adUnitId);
            }

            @Override
            public void onRewardedVideoPlaybackError(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
                Log.d(TAG, "onRewardedVideoPlaybackError: 激励视频播放失败回调:" + errorCode);
            }

            @Override
            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                Log.d(TAG, "onRewardedVideoClicked: 激励视频被点击:" + adUnitId);
            }

            @Override
            public void onRewardedVideoClosed(@NonNull String adUnitId) {
                Log.d(TAG, "onRewardedVideoClosed: 激励视频被关闭:" + adUnitId);
            }

            @Override
            public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
                Log.d(TAG, "onRewardedVideoCompleted: 激励视频播放完成: ");
            }
        });
    }


    private void onClickView() {
        loadTv.setOnClickListener(view -> {
            Log.d(TAG, "onClickView: 开始缓存激励视频: " + rewardADId);
            MoPubRewardedVideos.loadRewardedVideo(rewardADId);
        });


        playTv.setOnClickListener(view -> {
            if (MoPubRewardedVideos.hasRewardedVideo(rewardADId)) {
                MoPubRewardedVideos.showRewardedVideo(rewardADId);
            }else{
                Toast.makeText(RewardVideoADActivity.this,"请先缓存激励视频广告",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
