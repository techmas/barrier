package ru.techmas.barrier.api.endpoints

import io.reactivex.Observable
import retrofit2.http.*
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.api.models.StateResponse


/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

interface Barrier {

    @FormUrlEncoded
    @POST("api.php")
    fun getBarriers(@Field("login") number: String,
                    @Field("key") token: String,
                    @Field("barrier") barrier: String): Observable<Barriers>

    @FormUrlEncoded
    @POST("api.php")
    fun openBarrier(@Field("login") number: String,
                    @Field("key") token: String,
                    @Field("command") command: String,
                    @Field("barrier_id") barrier: Int): Observable<StateResponse>
}
