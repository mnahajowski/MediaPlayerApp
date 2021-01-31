package pl.pwr.andz1.i00000

import Services.NotificationActionService
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


private lateinit var notification: NotificationCompat.Builder

class CreateNotification {

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val ACTION_PREVIOUS = "action_previous"
        const val ACTION_PLAY = "action_play"
        const val ACTION_NEXT = "action_next"

        fun create_notification(context: Context, songId: Int, play_button: Int, pos: Int, size: Int,
        name: String) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationManagerCompat = NotificationManagerCompat.from(context)
                var mediaSessionCompat : MediaSessionCompat
                var media =  MediaSessionCompat(context, "Tag")
                var pendingIntentPrevious : PendingIntent?
                var drw_previous : Int

                if (pos == 0)
                {
                    pendingIntentPrevious = null
                    drw_previous = 0
                } else {
                    var intentPrevious = Intent(context, NotificationActionService::class.java)
                    intentPrevious.action = ACTION_PREVIOUS
                    pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious,
                        PendingIntent.FLAG_UPDATE_CURRENT)
                    drw_previous = R.drawable.ic_baseline_skip_previous_24
                }

                var intentPlay= Intent(context, NotificationActionService::class.java)
                intentPlay.action = ACTION_PLAY
                var pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay,
                    PendingIntent.FLAG_UPDATE_CURRENT)
                var pendingIntentNext : PendingIntent?
                var drw_next : Int

                if (pos == size)
                {
                    pendingIntentNext = null
                    drw_next = 0
                } else {
                    var intentNext = Intent(context, NotificationActionService::class.java)
                    intentNext.action = ACTION_NEXT
                    pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext,
                        PendingIntent.FLAG_UPDATE_CURRENT)
                    drw_next = R.drawable.ic_baseline_skip_next_24
                }

                notification = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Title")
                    .setContentText(name)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .addAction(drw_previous, "Previous", pendingIntentPrevious)
                    .addAction(R.drawable.ic_baseline_play_arrow_24, "Play", pendingIntentPlay)
                    .addAction(drw_next, "Next", pendingIntentNext)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)

                notificationManagerCompat.notify(1, notification.build())

            }

        }
    }

}