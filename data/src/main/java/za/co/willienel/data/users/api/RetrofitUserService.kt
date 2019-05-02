package za.co.willienel.data.users.api

import io.reactivex.Single
import retrofit2.http.GET
import za.co.willienel.data.users.model.UserEntity

interface RetrofitUserService {

    @GET("users")
    fun getUsers(): Single<List<UserEntity>>
}