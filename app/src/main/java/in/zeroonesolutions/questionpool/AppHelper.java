package in.zeroonesolutions.questionpool;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class AppHelper extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(getApplicationContext(),getString(R.string.admob_app_id));
    }
}
