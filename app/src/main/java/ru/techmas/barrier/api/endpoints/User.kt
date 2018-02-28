package ru.techmas.barrier.api.endpoints

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import ru.techmas.barrier.api.ApiResponse

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

interface User {


    //    // TODO: 09.11.2016 init here!
    @get:GET("album/list")
    val test: Observable<Response<ApiResponse<String>>>
}
