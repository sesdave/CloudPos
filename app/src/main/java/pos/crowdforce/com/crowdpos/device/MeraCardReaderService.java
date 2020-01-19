package pos.crowdforce.com.crowdpos.device;

import android.content.Context;

import com.ajibigad.mera_hardware_library.services.CardReaderService;
import com.ajibigad.topwise_hardware_library.services.TopwiseCardReaderService;

public class MeraCardReaderService {
    public static CardReaderService getInstance(Context context){
        return TopwiseCardReaderService.getInstance(context);
    }
}
