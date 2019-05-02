package za.co.willienel.domain.users.usecases

import io.reactivex.Single
import za.co.willienel.domain.users.api.UserApi
import za.co.willienel.domain.users.models.User

class GetUsersUseCase(private val userApi: UserApi) {

    fun getUsers(): Single<List<User>> {
        
        return userApi.getUsers()
    }
}