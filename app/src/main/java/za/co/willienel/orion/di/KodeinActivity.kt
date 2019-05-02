package za.co.willienel.orion.di

import android.os.Bundle
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.kodein
import za.co.willienel.orion.ui.core.BaseActivity

abstract class KodeinActivity : BaseActivity(), KodeinAware {

    override val kodein by kodein()
    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger.trigger()
    }
}