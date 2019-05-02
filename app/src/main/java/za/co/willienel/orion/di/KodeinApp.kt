package za.co.willienel.orion.di

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import za.co.willienel.orion.di.modules.*

abstract class KodeinApp : Application(), KodeinAware {

    override val kodein by Kodein.lazy {

        import(androidCoreModule(this@KodeinApp))

        import(factoryModule)
        import(networkModule)
        import(retrofitServiceModule)
        import(apiModule)

        import(mainModule)
    }
}