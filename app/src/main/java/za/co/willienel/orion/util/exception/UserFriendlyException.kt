package za.co.willienel.orion.util.exception

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UserFriendlyException(private val throwable: Throwable) {

    companion object {

        const val GENERIC_ERROR_MESSAGE: String = "Something went wrong! Please try again later"

        const val NETWORK_ERROR_MESSAGE: String =
            "We were unable to query the data, please check your internet connection"

        val USER_FRIENDLY_MESSAGES: Map<String, String> = mutableMapOf(
            Pair(UnknownHostException::class.java.simpleName, NETWORK_ERROR_MESSAGE),
            Pair(ConnectException::class.java.simpleName, NETWORK_ERROR_MESSAGE),
            Pair(SocketTimeoutException::class.java.simpleName, NETWORK_ERROR_MESSAGE)
        )
    }

    fun getMessage(): String {

        return USER_FRIENDLY_MESSAGES.getOrElse(throwable::class.java.simpleName) {
            throwable.message ?: GENERIC_ERROR_MESSAGE
        }
    }
}