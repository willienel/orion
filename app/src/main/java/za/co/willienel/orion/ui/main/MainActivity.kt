package za.co.willienel.orion.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.kodein.di.generic.instance
import za.co.willienel.orion.R
import za.co.willienel.orion.di.KodeinActivity

class MainActivity : KodeinActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    private val viewSubscriptions: CompositeDisposable = CompositeDisposable()

    private lateinit var mainView: MainView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = MainView(LayoutInflater.from(this), R.layout.activity_main)

        setContentView(mainView.getRootView())

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun registerViewListeners() {

        addViewSubscription(mainView
            .queryNamesClicked()
            .subscribe {
                mainViewModel.queryNames()
            }
        )

        addViewSubscription(mainView
            .queryEmailAddressClicked()
            .subscribe {
                mainViewModel.queryEmailAddress()
            }
        )
    }

    override fun registerViewModelListeners() {

        mainViewModel
            .nameListUpdates()
            .observe(this, Observer { namesListEvent ->
                namesListEvent.getContentIfNotHandled()?.let { namesList ->
                    mainView.showNames(namesList)
                }
            })

        mainViewModel
            .emailAddressListUpdates()
            .observe(this, Observer { emailAddressesListEvent ->
                emailAddressesListEvent.getContentIfNotHandled()?.let { emailAddressesList ->
                    mainView.showEmailAddresses(emailAddressesList)
                }
            })
    }

    override fun unregisterViewListeners() {
        clearViewSubscriptions()
    }

    override fun unregisterViewModelListeners() {
    }

    private fun addViewSubscription(disposable: Disposable) {
        viewSubscriptions.add(disposable)
    }

    private fun clearViewSubscriptions() {
        viewSubscriptions.clear()
    }

    override fun onDestroy() {
        mainView.onDestroy()
        super.onDestroy()
    }
}
