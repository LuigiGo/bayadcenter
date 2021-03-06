package com.androidsystems.bayadcenterapp.core.utils

import com.androidsystems.bayadcenterapp.core.utils.Status.ERROR
import com.androidsystems.bayadcenterapp.core.utils.Status.LOADING
import com.androidsystems.bayadcenterapp.core.utils.Status.SUCCESS

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}