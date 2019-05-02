package za.co.willienel.orion.di.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import za.co.willienel.domain.users.usecases.GetUsersUseCase
import za.co.willienel.orion.ui.main.MainViewModel

val mainModule = Kodein.Module("mainModule") {

    bind<GetUsersUseCase>() with provider { GetUsersUseCase(instance()) }

    bind<MainViewModel>(tag = MainViewModel::class.java.simpleName) with provider { MainViewModel(instance()) }
}