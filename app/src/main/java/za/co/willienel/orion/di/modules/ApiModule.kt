package za.co.willienel.orion.di.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import za.co.willienel.data.users.api.RetrofitUserApi
import za.co.willienel.domain.users.api.UserApi

val apiModule = Kodein.Module("apiModule") {

    bind<UserApi>() with provider { RetrofitUserApi(instance()) }
}