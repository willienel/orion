package za.co.willienel.data.users.api

import io.reactivex.Observable
import io.reactivex.Single
import za.co.willienel.data.users.mapper.UserMapper
import za.co.willienel.domain.users.api.UserApi
import za.co.willienel.domain.users.models.User

class RetrofitUserApi(private val userService: RetrofitUserService) : UserApi {

    override fun getUsers(): Single<List<User>> {

        return userService
            .getUsers()
            .flatMap { userEntityList ->
                Observable.fromIterable(userEntityList)
                    .map { userEntity -> UserMapper.fromUserEntity(userEntity) }
                    .toList()
            }
    }
}