package mc.com.codelab

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notify.*
import android.content.Intent
import android.app.PendingIntent
import android.graphics.BitmapFactory


class NotifyActivity : BaseActivity(R.layout.activity_notify) {
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private var notifyManager: NotificationManager? = null
    private val NOTIFICATION_ID = 0

    init {
       super.log("${javaClass.simpleName} init..")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()
        btn_notify_create.setOnClickListener{sendNotification()}
        btn_notify_cancel.setOnClickListener{
            notifyManager!!.cancel(NOTIFICATION_ID)
            setNotificationButtonState(true, false, false)
        }

        setNotificationButtonState(true, false, false)
    }
    fun createNotificationChannel() {

        log("Create Notification Channel..")

        notifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "CodeLab Notification",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notification from CodeLab!"

            notifyManager!!.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        //2-  content intent with notification
        val notificationIntent = Intent(this, StartActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Hello MC!")
            .setContentText("I'm a cool notification!!\n click me to launch app.")
            .setSmallIcon(R.drawable.ic_notify_icon)
                //2- with integred intent
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
    }
    fun sendNotification(){
        val notifyBuilder = getNotificationBuilder()
        notifyManager!!.notify(NOTIFICATION_ID, notifyBuilder.build())

        setNotificationButtonState(false, true, true)
    }

    fun updateNotification(){
        val androidImage = BitmapFactory.decodeResource(resources, R.drawable.notify_logo)

        var notifyBuilder = getNotificationBuilder();

        notifyBuilder.setStyle(NotificationCompat.BigPictureStyle()
            .bigPicture(androidImage)
            .setBigContentTitle("Notification Updated!"))

        notifyManager!!.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(false, false, true)
    }

    fun setNotificationButtonState(
        isNotifyEnabled: Boolean,
        isUpdateEnabled: Boolean,
        isCancelEnabled: Boolean
    ) {
        btn_notify_create.isEnabled = isNotifyEnabled
        btn_notify_update.isEnabled = isUpdateEnabled
        btn_notify_cancel.isEnabled = isCancelEnabled
    }

}
