package za.co.willienel.orion.ui.main

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes

class MainView(layoutInflater: LayoutInflater, @LayoutRes layoutResourceId: Int) {

    private val rootView: View = layoutInflater.inflate(layoutResourceId, null, false)

    fun getRootView(): View {
        return rootView
    }
}