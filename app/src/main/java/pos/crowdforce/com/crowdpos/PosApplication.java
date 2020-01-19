package pos.crowdforce.com.crowdpos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import pos.crowdforce.com.crowdpos.device.MeraSystemService;
import pos.crowdforce.com.crowdpos.utils.HandlerUtils;
import pos.crowdforce.com.crowdpos.utils.ToasterUtils;

/**
 * Created by ASUS on 1/16/2020.
 */

public class PosApplication extends Application {

    private static final String TAG = "lakaladebug: " + PosApplication.class.getSimpleName();

    private static PosApplication mPosApplication;

    //public ConsumeData mConsumeData;
//    SystemService MeraSystemService;
    private Context mContext;

    public static PosApplication getApp() {
        return mPosApplication;
    }
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        this.mContext = getApplicationContext();
        mPosApplication = this;
        if (!MeraSystemService.initialize(this)) {
            Toast.makeText(this, "Failed to initialize device", Toast.LENGTH_LONG).show();
            HandlerUtils.postDelayedTask(new Runnable() {
                @Override
                public void run() {
                    ToasterUtils.showMessage(getApplicationContext(), "Trying to initialize system again");
                    System.out.println("Result: " + MeraSystemService.getInstance(getApplicationContext()).initialize());
                }
            }, 1000);
        }
    }

    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "onTerminate");
        System.exit(0);
    }

}
