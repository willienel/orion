package za.co.willienel.orion.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import za.co.willienel.domain.users.usecases.GetUsersUseCase
import za.co.willienel.orion.util.exception.UserFriendlyException
import za.co.willienel.orion.util.livedata.Event

class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    companion object {

        private const val USERNAME_SAMANTHA: String = "Samantha"
    }

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    private val namesLiveData: MutableLiveData<Event<List<String>>> = MutableLiveData()
    private val emailAddressesLiveData: MutableLiveData<Event<List<String>>> = MutableLiveData()
    private val errorMessageLiveData: MutableLiveData<Event<String>> = MutableLiveData()

    fun queryNames() {

        addSubscription(
            getUsersUseCase
                .getUsers()
                .flatMap { userList ->
                    Observable.fromIterable(userList)
                        .map { user -> user.name }
                        .toList()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ namesList ->
                    namesLiveData.value = Event(namesList)
                }, { error ->
                    Timber.e(error)
                    errorMessageLiveData.value = Event(UserFriendlyException(error).getMessage())
                })
        )
    }

    fun queryEmailAddress() {

        addSubscription(
            getUsersUseCase
                .getUsers()
                .flatMap { userList ->
                    Observable.fromIterable(userList)
                        .filter { user ->
                            user.username == USERNAME_SAMANTHA
                        }
                        .map { user -> user.email }
                        .toList()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ emailList ->
                    emailAddressesLiveData.value = Event(emailList)
                }, { error ->
                    Timber.e(error)
                    errorMessageLiveData.value = Event(UserFriendlyException(error).getMessage())
                })
        )
    }

    private fun addSubscription(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    private fun clearSubscriptions() {
        subscriptions.clear()
    }

    fun nameListUpdates(): LiveData<Event<List<String>>> {
        return namesLiveData
    }

    fun emailAddressListUpdates(): LiveData<Event<List<String>>> {
        return emailAddressesLiveData
    }

    fun errorMessageUpdates(): LiveData<Event<String>> {
        return errorMessageLiveData
    }

    override fun onCleared() {
        super.onCleared()
        clearSubscriptions()
    }
}