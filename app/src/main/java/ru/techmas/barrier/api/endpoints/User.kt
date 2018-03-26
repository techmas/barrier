package ru.techmas.barrier.api.endpoints

import io.reactivex.Observable
import retrofit2.http.*
import ru.techmas.barrier.api.models.StateResponse


/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

interface User {

    @FormUrlEncoded
    @POST("api.php")
    fun sendSms(@Field("number") number:String): Observable<StateResponse>

    @FormUrlEncoded
    @POST("api.php")
    fun checkCode(@Field("number") number:String,
                  @Field("smsCode") code:String): Observable<StateResponse>
}
