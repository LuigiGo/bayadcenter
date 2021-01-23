package com.androidsystems.bayadcenterapp.core.base

import com.androidsystems.bayadcenterapp.core.utils.NoConnectivityException
import com.androidsystems.bayadcenterapp.core.utils.Resource
import kotlinx.coroutines.Deferred
import retrofit2.HttpException

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Deferred<T>): Resource<T> {
        return try {
            val response = call().await()
            Resource.success(response)
        } catch (e: Exception) {
            return when (e) {
                is NoConnectivityException -> Resource.error("No internet connection", null)
                is HttpException -> Resource.error(parseErrorResponse(e), null)
                else -> Resource.error(e.message, null)
            }
        }
    }

    private fun parseErrorResponse(e: HttpException): String {
        return when (e.code()) {
            401 -> "Unauthorized Access"
            403 -> "Forbidden"
            404 -> "Not Found"
            500 -> "Internal Server Error"
            else -> e.response().errorBody()?.charStream()?.readText().toString()
        }
    }
}