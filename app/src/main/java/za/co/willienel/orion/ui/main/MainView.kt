package za.co.willienel.orion.ui.main

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainView(layoutInflater: LayoutInflater, @LayoutRes layoutResourceId: Int) {

    companion object {

        const val POSITIVE_TEXT_OK: String = "OK"
    }

    private val rootView: View = layoutInflater.inflate(layoutResourceId, null, false)

    private val queryNamesClickListener: PublishSubject<View> = PublishSubject.create()
    private val queryEmailAddressClickListener: PublishSubject<View> = PublishSubject.create()

    private lateinit var alertDialog: AlertDialog

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

        showAlertDialog(namesList.joinToString("\n"))
    }

    fun showEmailAddresses(emailAddressList: List<String>) {

        Timber.d("Email Addresses: $emailAddressList")

        showAlertDialog(emailAddressList.joinToString("\n"))
    }

    fun queryNamesClicked(): Flowable<View> {

        return queryNamesClickListener.toFlowable(BackpressureStrategy.LATEST)
    }

    fun queryEmailAddressClicked(): Flowable<View> {

        return queryEmailAddressClickListener.toFlowable(BackpressureStrategy.LATEST)
    }

    private fun showAlertDialog(message: String) {

        dismissDialogIfShowing()

        alertDialog = AlertDialog.Builder(rootView.context)
            .setMessage(message)
            .setPositiveButton(POSITIVE_TEXT_OK) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()

        alertDialog.show()
    }

    private fun dismissDialogIfShowing() {

        if (!::alertDialog.isInitialized) {
            return
        }

        if (alertDialog.isShowing) {
            alertDialog.dismiss()
        }
    }

    fun onDestroy() {
        dismissDialogIfShowing()
    }

    fun getRootView(): View {
        return rootView
    }
}