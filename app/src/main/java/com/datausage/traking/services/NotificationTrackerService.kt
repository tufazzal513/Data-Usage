package com.datausage.traking.services

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.datausage.traking.data.AppDatabase
import com.datausage.traking.data.NotificationRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationTrackerService : NotificationListenerService() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val dao by lazy { AppDatabase.getDatabase(this).dao() }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            val extras = it.notification.extras
            val title = extras.getString("android.title") ?: ""
            val text = extras.getCharSequence("android.text")?.toString() ?: ""
            scope.launch {
                dao.insertNotification(
                    NotificationRecord(
                        packageName = it.packageName,
                        title = title,
                        text = text,
                        timestamp = System.currentTimeMillis(),
                        isDismissed = false
                    )
                )
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        // Handle dismissed notifications
    }
}
