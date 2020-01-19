package pos.crowdforce.com.crowdpos.device;

import android.content.Context;

import com.ajibigad.mera_hardware_library.services.CardReaderService;
import com.ajibigad.mera_hardware_library.services.EmvService;
import com.ajibigad.topwise_hardware_library.services.TopwiseCardReaderService;
import com.ajibigad.topwise_hardware_library.services.TopwiseEmvService;

public class MeraEmvService {
    public static EmvService getInstance(Context context){
        return TopwiseEmvService.getInstance(context);
    }
}
