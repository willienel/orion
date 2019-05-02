package za.co.willienel.orion.ui.main

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import za.co.willienel.domain.users.usecases.GetUsersUseCase

class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    fun loadUsers() {

        addSubscription(
            getUsersUseCase
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users -> Timber.d("$users") },
                    { error -> Timber.e(error) })
        )
    }

    private fun addSubscription(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}