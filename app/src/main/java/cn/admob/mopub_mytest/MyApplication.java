package cn.admob.mopub_mytest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;

import java.util.HashMap;
import java.util.Map;

import static com.mopub.common.logging.MoPubLog.LogLevel.DEBUG;
import static com.mopub.common.logging.MoPubLog.LogLevel.INFO;

/**
 * @Description:
 * @Author: zhangsw
 * @CreateDate: 2019-10-14 09:56
 */
public class MyApplication extends Application {

    private static final String TAG = "Mopub>>MyApplication";
    
    @Override
    public void onCreate() {
        super.onCreate();
        initMopubSDK();
    }

    private void initMopubSDK() {
        // configurations required to initialize
//        Map<String, String> mediatedNetworkConfiguration1 = new HashMap<>();
//        mediatedNetworkConfiguration1.put("<custom-adapter-class-data-key>", "<custom-adapter-class-data-value>");
//        Map<String, String> mediatedNetworkConfiguration2 = new HashMap<>();
//        mediatedNetworkConfiguration2.put("<custom-adapter-class-data-key>", "<custom-adapter-class-data-value>");

        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder("b195f8dd8ded45fe847ad89ed1d016da");
//        SdkConfiguration.Builder sdkConfiguration = new SdkConfiguration.Builder("222220000");

        if (BuildConfig.DEBUG) {
            sdkConfiguration.withLogLevel(DEBUG);
        } else {
            sdkConfiguration.withLogLevel(INFO);
        }

        MoPub.initializeSdk(this, sdkConfiguration.build(), new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                /* MoPub SDK initialized.
               Check if you should show the consent dialog here, and make your ad requests. */
                Log.d(TAG, "onInitializationFinished: 初始化结束");
                Toast.makeText(MyApplication.this, "初始化成功", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
