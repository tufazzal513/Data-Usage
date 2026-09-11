package com.datausage.traking.services

import android.content.Intent
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class TrackingTileService : TileService() {
    override fun onStartListening() {
        super.onStartListening()
        // Check if tracking service is running
        val tile = qsTile
        tile.state = Tile.STATE_ACTIVE // Or INACTIVE based on state
        tile.updateTile()
    }

    override fun onClick() {
        super.onClick()
        val tile = qsTile
        if (tile.state == Tile.STATE_ACTIVE) {
            tile.state = Tile.STATE_INACTIVE
            // Stop service
            stopService(Intent(this, DataTrackingService::class.java))
        } else {
            tile.state = Tile.STATE_ACTIVE
            // Start service
            startForegroundService(Intent(this, DataTrackingService::class.java))
        }
        tile.updateTile()
    }
}

class SummaryTileService : TileService() {
    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile
        tile.label = "54 MB Today" // Dummy fetched data
        tile.state = Tile.STATE_ACTIVE
        tile.updateTile()
    }

    override fun onClick() {
        super.onClick()
        // Open main app
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.let { startActivityAndCollapse(it) }
    }
}
