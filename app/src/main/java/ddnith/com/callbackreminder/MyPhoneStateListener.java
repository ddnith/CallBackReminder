package ddnith.com.callbackreminder;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DEEPAK on 6/21/2015.
 */
public class MyPhoneStateListener extends PhoneStateListener {

    public static Boolean phoneRinging = false;
    public static Boolean phoneOffhook = false;
    private Context mContext;

    public MyPhoneStateListener(Context context){
           mContext = context;
    }

    public void onCallStateChanged(int state, String incomingNumber) {

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.d("DEBUG", "IDLE");
                if(phoneRinging || phoneOffhook){
                    Log.d("DEBUG", "Call Was Rejected Starting Activity");
//                    Intent i = new Intent(mContext,MyActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(i);
                    mContext.startService(new Intent(mContext, ReminderHeadNotificationService.class));

                }
                phoneRinging = false;
                phoneOffhook = false;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.d("DEBUG", "OFFHOOK");
                phoneRinging = false;
                phoneOffhook = true;
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.d("DEBUG", "RINGING");
                phoneRinging = true;
                phoneOffhook = false;

                break;
        }
    }

}
