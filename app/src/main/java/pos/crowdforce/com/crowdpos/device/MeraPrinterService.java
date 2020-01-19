package pos.crowdforce.com.crowdpos.device;

import android.content.Context;

import com.ajibigad.mera_hardware_library.services.PrinterService;
import com.ajibigad.topwise_hardware_library.services.TopwisePrinterService;

public class MeraPrinterService {
    public static PrinterService getInstance(Context context) {
        return TopwisePrinterService.getInstance(context);
    }
}
