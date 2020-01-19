package pos.crowdforce.com.crowdpos;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.ajibigad.mera_hardware_library.PinpadInputDisplay;
import com.ajibigad.mera_hardware_library.models.emv.EmvTransactionDetails;
import com.ajibigad.mera_hardware_library.models.emv.EmvTransactionStatus;
import com.ajibigad.mera_hardware_library.services.EmvService;
import com.ajibigad.mera_hardware_library.services.PrinterService;
import com.google.zxing.common.StringUtils;

import java.math.BigDecimal;

import pos.crowdforce.com.crowdpos.device.MeraEmvService;
import pos.crowdforce.com.crowdpos.device.MeraPrinterService;
import pos.crowdforce.com.crowdpos.device.MeraSystemService;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    EmvService emvService;
    boolean mIsAuthenticated=false;
    //This guy is suppose to be a BigDecimal reason for the error
    //String transactionAmount="2000";
    BigDecimal transactionAmount=new BigDecimal("2.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emvService = MeraEmvService.getInstance(this);

        //pinInputDisplayHandler Handler
        Handler pinInputDisplayHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message message){
                Bundle bundle = message.getData();
                String input = bundle.getString(PinpadInputDisplay.MESSAGE_KEY);
                //TODO  Initialize a textview that will be used to update the progress of the pin input here
                //TODO  then call setText on the text view setting the input above
            }
        };

        EmvTransactionDetails emvTransactionDetails = mIsAuthenticated ? emvService.processTransaction(transactionAmount, new PinpadInputDisplay(pinInputDisplayHandler)) : emvService.getCardNo(transactionAmount);

        if (emvTransactionDetails == null || emvTransactionDetails.getTransactionStatus() == EmvTransactionStatus.FAILED) {
           // publishProgress("TransactionItem failed");
        } else {
            if (TextUtils.isEmpty(emvTransactionDetails.getPinblock()) && getContext() != null) {
                //Mean offline pin was entered
                //Call on the UI thread
                //getActivity().runOnUiThread(() -> AlertDialogUtilites.showToast(getContext(), "PIN OK"));
            }
        }
    }
    public void Printer(){
        PrinterService printerService = MeraPrinterService.getInstance(this);
        printerService.open();
        //TODO Make printerService a global variable and call close() function in the onDestroy() callback of the activity

       // printerService.
        //printerservice.close();
    }
}
