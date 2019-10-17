package cn.admob.mopub_mytest;

import androidx.appcompat.app.AppCompatActivity;
import cn.admob.mopub_mytest.adactivity.BannerADActivity;
import cn.admob.mopub_mytest.adactivity.IntersitialADActivity;
import cn.admob.mopub_mytest.adactivity.NativeADActivity;
import cn.admob.mopub_mytest.adactivity.NativeVideoADActivity;
import cn.admob.mopub_mytest.adactivity.RewardVideoADActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Mopub>>";

    private TextView bannerADTv, intersitialADTv, nativeADTv, nativeVideoADTv, rewardVideoADTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        onClickView();
    }


    private void initView() {
        bannerADTv = findViewById(R.id.bannerAD);
        intersitialADTv = findViewById(R.id.intersitialAD);
        nativeADTv = findViewById(R.id.nativeAD);
        nativeVideoADTv = findViewById(R.id.nativeRewardAD);
        rewardVideoADTv = findViewById(R.id.rewardVideoAD);
    }


    private void onClickView() {
        bannerADTv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, BannerADActivity.class));
        });

        intersitialADTv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, IntersitialADActivity.class));
        });

        nativeADTv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, NativeADActivity.class));
        });

        nativeVideoADTv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, NativeVideoADActivity.class));
        });

        rewardVideoADTv.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RewardVideoADActivity.class));
        });
    }
}
