package za.co.willienel.orion.di.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit
import za.co.willienel.data.users.api.RetrofitUserService

val retrofitServiceModule = Kodein.Module("retrofitServiceModule") {

    bind<RetrofitUserService>() with provider {

        instance<Retrofit>().create(RetrofitUserService::class.java)
    }
}