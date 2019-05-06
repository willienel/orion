package za.co.willienel.orion.ui.core

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun registerViewListeners()
    abstract fun unregisterViewListeners()

    abstract fun registerViewModelListeners()
    abstract fun unregisterViewModelListeners()

    override fun onResume() {
        super.onResume()
        registerViewListeners()
        registerViewModelListeners()
    }

    override fun onPause() {
        super.onPause()
        unregisterViewListeners()
        unregisterViewModelListeners()
    }
}