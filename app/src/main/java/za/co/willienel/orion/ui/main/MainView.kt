package za.co.willienel.orion.ui.main

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.view.*
import za.co.willienel.orion.R

class MainView(layoutInflater: LayoutInflater, @LayoutRes layoutResourceId: Int) {

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

        if (namesList.isEmpty()) {
            showAlertDialog(rootView.context.getString(R.string.error_message_no_names))
            return
        }

        showAlertDialog(namesList.joinToString("\n"))
    }

    fun showEmailAddresses(emailAddressList: List<String>) {

        if (emailAddressList.isEmpty()) {
            showAlertDialog(rootView.context.getString(R.string.error_message_no_email_addresses))
            return
        }

        showAlertDialog(emailAddressList.joinToString("\n"))
    }

    fun showErrorMessage(message: String) {
        showAlertDialog(message)
    }

    fun queryNamesClicked(): Flowable<View> {

        return queryNamesClickListener.toFlowable(BackpressureStrategy.LATEST)
    }

    fun queryEmailAddressClicked(): Flowable<View> {

        return queryEmailAddressClickListener.toFlowable(BackpressureStrategy.LATEST)
    }

    fun showProgress(showProgress: Boolean) {

        rootView.progress.visibility = if (showProgress) View.VISIBLE else View.GONE
    }

    private fun showAlertDialog(message: String) {

        dismissDialogIfShowing()

        alertDialog = AlertDialog.Builder(rootView.context)
            .setMessage(message)
            .setPositiveButton(rootView.context.getString(R.string.ok)) { dialogInterface, _ ->
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