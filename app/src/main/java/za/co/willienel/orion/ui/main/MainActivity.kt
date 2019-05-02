package za.co.willienel.orion.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.generic.instance
import za.co.willienel.orion.R
import za.co.willienel.orion.di.KodeinActivity

class MainActivity : KodeinActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    private lateinit var mainView: MainView
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = MainView(LayoutInflater.from(this), R.layout.activity_main)

        setContentView(mainView.getRootView())

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.loadUsers()
    }

    override fun registerViewListeners() {
    }

    override fun unregisterViewListeners() {
    }

    override fun registerViewModelListeners() {
    }

    override fun unregisterViewModelListeners() {
    }
}
