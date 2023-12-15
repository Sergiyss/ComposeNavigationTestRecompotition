package ua.dev.webnauts.compose_navigation_test_recompotition.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.compose_navigation_test_recompotition.network.network_monitor.ConnectivityManagerNetworkMonitor
import ua.dev.webnauts.compose_navigation_test_recompotition.network.network_monitor.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
