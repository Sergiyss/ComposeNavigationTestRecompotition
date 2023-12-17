package ua.dev.webnauts.compose_navigation_test_recompotition

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import jp.wasabeef.takt.Takt
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    companion object {
        private var isMainActivityStarted: Boolean = false

        fun isMainActivityStarted(): Boolean {
            return isMainActivityStarted
        }

        var fcmToken: String? = null

    }
    override fun onCreate() {
        super.onCreate()
        /*in some XIAOMI devices seems to be necessary*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Timber.plant(Timber.DebugTree())


                Takt.stock(this)
            .color(R.color.purple_200)
            .size(16f)
    }
}