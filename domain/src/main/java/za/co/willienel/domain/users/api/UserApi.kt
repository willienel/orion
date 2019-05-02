package za.co.willienel.domain.users.api

import io.reactivex.Single
import za.co.willienel.domain.users.models.User

interface UserApi {

    fun getUsers(): Single<List<User>>
}