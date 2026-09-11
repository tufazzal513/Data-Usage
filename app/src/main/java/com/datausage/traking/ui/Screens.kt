package com.datausage.traking.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.datausage.traking.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val topConsumers = viewModel.topDataConsumers.collectAsState().value
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Data Usage Summary", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Top Consumers", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(topConsumers) { app ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(app.packageName)
                        Text("${app.totalBytes / (1024 * 1024)} MB")
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationsScreen(viewModel: MainViewModel) {
    val notifications = viewModel.notifications.collectAsState().value
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Notifications History", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(notifications) { notif ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(notif.packageName, style = MaterialTheme.typography.labelSmall)
                            Text(formatter.format(Date(notif.timestamp)), style = MaterialTheme.typography.labelSmall)
                        }
                        Text(notif.title, style = MaterialTheme.typography.titleMedium)
                        Text(notif.text, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Check permissions */ }) {
            Text("Grant Usage Access")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Export */ }) {
            Text("Export Data (JSON)")
        }
    }
}
