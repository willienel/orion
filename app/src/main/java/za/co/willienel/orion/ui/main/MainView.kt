package za.co.willienel.orion.ui.main

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainView(layoutInflater: LayoutInflater, @LayoutRes layoutResourceId: Int) {

    private val rootView: View = layoutInflater.inflate(layoutResourceId, null, false)

    private val queryNamesClickListener: PublishSubject<View> = PublishSubject.create()
    private val queryEmailAddressClickListener: PublishSubject<View> = PublishSubject.create()

    init {

        rootView.btnQueryNames.setOnClickListener { view ->

            queryNamesClickListener.onNext(view)
        }

        rootView.btnQueryEmailAddress.setOnClickListener { view ->

            queryEmailAddressClickListener.onNext(view)
        }
    }

    fun showNames(namesList: List<String>) {

        Timber.d("Names: $namesList")
    }

    fun showEmailAddresses(emailAddressList: List<String>) {

        Timber.d("Email Addresses: $emailAddressList")
    }

    fun queryNamesClicked(): Flowable<View> {

        return queryNamesClickListener.toFlowable(BackpressureStrategy.LATEST)
    }

    fun queryEmailAddressClicked(): Flowable<View> {

        return queryEmailAddressClickListener.toFlowable(BackpressureStrategy.LATEST)
    }

    fun getRootView(): View {
        return rootView
    }
}