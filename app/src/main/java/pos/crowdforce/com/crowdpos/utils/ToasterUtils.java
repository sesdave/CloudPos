package pos.crowdforce.com.crowdpos.utils;

import android.content.Context;
import android.widget.Toast;

public final class ToasterUtils {

    private ToasterUtils() {

    }

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
