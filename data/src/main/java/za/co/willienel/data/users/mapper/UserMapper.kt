package za.co.willienel.data.users.mapper

import za.co.willienel.data.users.model.UserEntity
import za.co.willienel.domain.users.models.User

class UserMapper {

    companion object {

        fun fromUserEntity(userEntity: UserEntity): User {
            return User(userEntity.id, userEntity.name, userEntity.username, userEntity.email)
        }
    }
}