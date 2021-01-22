package com.androidsystems.bayadcenterapp.core.base

import com.androidsystems.bayadcenterapp.core.utils.Resource
import kotlinx.coroutines.Deferred

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Deferred<T>): Resource<T> {
        return try {
            val response = call().await()
            Resource.success(response)
        } catch (e: Exception) {
            return when (e) {
//                is NoConnectivityException -> Resource.error("No internet connection", null)
                else -> Resource.error(
                    "Unknown Exception",
                    null
                )
            }
        }
    }
}