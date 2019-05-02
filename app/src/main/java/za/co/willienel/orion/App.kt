package za.co.willienel.orion

import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import za.co.willienel.orion.di.KodeinApp

class App : KodeinApp() {

    override fun onCreate() {
        setupStrictMode()
        super.onCreate()

        if (!setupLeakCanary()) {
            return
        }

        setupTimber()
    }

    private fun setupStrictMode() {

        if (BuildConfig.DEBUG) {

            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )

            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }

    private fun setupLeakCanary(): Boolean {

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return false
        }

        LeakCanary.install(this)

        return true
    }

    private fun setupTimber() {

        if (BuildConfig.DEBUG) {

            Timber.plant(Timber.DebugTree())
        }
    }
}