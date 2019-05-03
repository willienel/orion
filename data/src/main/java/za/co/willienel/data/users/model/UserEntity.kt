package za.co.willienel.data.users.model

import com.squareup.moshi.Json

data class UserEntity(

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "username")
    val username: String,

    @field:Json(name = "email")
    val email: String
)