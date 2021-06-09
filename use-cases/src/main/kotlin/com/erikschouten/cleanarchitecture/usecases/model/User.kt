package com.erikschouten.cleanarchitecture.usecases.model

import com.erikschouten.cleanarchitecture.domain.entity.user.Authorities
import com.erikschouten.cleanarchitecture.domain.entity.user.Email
import com.erikschouten.cleanarchitecture.domain.entity.user.Password
import com.erikschouten.cleanarchitecture.domain.entity.user.User
import com.erikschouten.cleanarchitecture.usecases.dependency.PasswordEncoder
import java.util.*

data class UserModelArray(
    val items: Array<UserModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserModelArray) return false

        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        return items.contentHashCode()
    }
}

data class UserModel(
    val id: UUID,
    val email: Email,
    val authorities: List<Authorities>,
) {
    constructor(user: User) : this(user.id, user.email, user.authorities)
}

data class CreateUserModel(
    val email: Email,
    val authorities: List<Authorities>,
    val password: Password,
) {
    fun toUser(encoder: PasswordEncoder) =
        User(email = email, authorities = authorities, password = encoder.encode(password))
}

data class LoginUserModel(
    val email: Email,
    val password: Password,
)
