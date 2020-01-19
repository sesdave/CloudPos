package pos.crowdforce.com.crowdpos.device;

import android.content.Context;

import com.ajibigad.mera_hardware_library.services.SystemService;
import com.ajibigad.topwise_hardware_library.services.TopwiseSystemService;

/*
This class initializes all system Library
If this initialization fails all the POS
Functionality might not work
 */
public class MeraSystemService {
    public static SystemService getInstance(Context context){
        return TopwiseSystemService.getInstance(context);
    }

    public static boolean initialize(Context context){
        return getInstance(context).initialize();
    }
}
