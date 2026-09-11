package com.datausage.traking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.datausage.traking.data.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).dao()

    val topDataConsumers = dao.getTopDataConsumers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        
    val notifications = dao.getAllNotifications()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val notificationCounts = dao.getNotificationCounts()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
