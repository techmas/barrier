package ru.techmas.barrier.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Bykov on 14.12.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class ApiResponse<T> {
    var status: String? = null
    @SerializedName("statusCode")
    var statusCode: String? = null
    @SerializedName("status_msg")
    var statusMsg: String? = null
    @SerializedName("data")
    var data: T? = null
        private set

    fun setDate(data: T) {
        this.data = data
    }
}
