package Services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationActionService : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            context?.sendBroadcast(Intent("TRACKS_TRACKS")
                .putExtra("actionname", intent.action))
        }
    }

}