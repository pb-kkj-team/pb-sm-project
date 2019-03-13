package pl.suwalki.zs2.thebestiilo.Service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.util.Log.d
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import pl.suwalki.zs2.thebestiilo.MainActivity
import pl.suwalki.zs2.thebestiilo.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val tag = "SERVICE"


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String?) {
        Log.d(tag, "Refreshed token: " + token!!)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(tag, "From: " + remoteMessage!!.from)
        Log.d(tag, "Notification Message Body: " + remoteMessage.notification!!.body)

        sendNotification(remoteMessage)

    }

    private fun sendNotification(remoteMessage: RemoteMessage) {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
            .setContentText(remoteMessage.notification!!.body)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_notify)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)



        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())


    }
}