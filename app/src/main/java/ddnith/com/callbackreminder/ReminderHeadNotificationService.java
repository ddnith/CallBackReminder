package ddnith.com.callbackreminder;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.os.Handler;

public class ReminderHeadNotificationService extends Service {

    private WindowManager windowManager;
    private ImageView reminderHead;
    private Handler mHandler;
    private final int FINISH_REMINDER_NOTIFICATION_HEAD = 0;
    private final int FINISH_REMINDER_NOTIFICATION_HEAD_DELAY = 5000;
    public ReminderHeadNotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        reminderHead = new ImageView(this);
        reminderHead.setImageResource(R.drawable.ic_launcher);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(reminderHead, params);
        reminderHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MyActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(i);
                stopSelf();
            }
        });
        messageHandler.sendEmptyMessageDelayed(FINISH_REMINDER_NOTIFICATION_HEAD,FINISH_REMINDER_NOTIFICATION_HEAD_DELAY);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (reminderHead != null) windowManager.removeView(reminderHead);
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("DEBUG", "Call Was Rejected Starting Activity");
            stopSelf();
        }
    };
}
