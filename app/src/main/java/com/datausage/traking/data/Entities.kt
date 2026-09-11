package com.datausage.traking.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "app_usage")
data class AppUsageRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val packageName: String,
    val timestamp: Long,
    val mobileDataBytes: Long,
    val wifiDataBytes: Long,
    val foregroundTimeMs: Long,
    val backgroundDataBytes: Long,
    val isRoaming: Boolean
)

@Entity(tableName = "notifications")
data class NotificationRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val packageName: String,
    val title: String,
    val text: String,
    val timestamp: Long,
    val isDismissed: Boolean = false
)

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val profileName: String,
    val isActive: Boolean
)

@Entity(tableName = "data_limits")
data class DataLimitSettings(
    @PrimaryKey val id: Int = 1,
    val monthlyLimitBytes: Long,
    val alertThresholdPercent: Int
)

@Dao
interface AppDao {
    @Insert
    suspend fun insertUsageRecord(record: AppUsageRecord)

    @Query("SELECT packageName, SUM(mobileDataBytes + wifiDataBytes) as totalBytes FROM app_usage GROUP BY packageName ORDER BY totalBytes DESC LIMIT 5")
    fun getTopDataConsumers(): Flow<List<AppUsageSummary>>

    @Insert
    suspend fun insertNotification(record: NotificationRecord)

    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): Flow<List<NotificationRecord>>

    @Delete
    suspend fun deleteNotification(record: NotificationRecord)

    @Query("SELECT packageName, COUNT(*) as count FROM notifications GROUP BY packageName ORDER BY count DESC")
    fun getNotificationCounts(): Flow<List<NotificationCount>>
}

data class AppUsageSummary(val packageName: String, val totalBytes: Long)
data class NotificationCount(val packageName: String, val count: Int)
