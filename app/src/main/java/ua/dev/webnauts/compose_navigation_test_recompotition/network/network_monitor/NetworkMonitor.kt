package ua.dev.webnauts.compose_navigation_test_recompotition.network.network_monitor

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}