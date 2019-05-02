package za.co.willienel.orion.ui.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.TT
import org.kodein.di.direct

class ViewModelFactory(private val injector: Kodein) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        injector.direct.Instance(TT(modelClass), tag = modelClass.simpleName)
}