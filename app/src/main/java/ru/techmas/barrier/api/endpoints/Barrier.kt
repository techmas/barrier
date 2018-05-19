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
    fun getCameras(@Field("login") number: String,
                    @Field("key") token: String,
                    @Field("cam") barrier: String): Observable<StateResponse>

    @FormUrlEncoded
    @POST("api.php")
    fun openAddedBarrier(@Field("login") number: String,
                    @Field("key") token: String,
                    @Field("from") from: String,
                    @Field("to") to: String): Observable<StateResponse>

    @FormUrlEncoded
    @POST("api.php")
    fun openBarrier(@Field("login") number: String,
                    @Field("key") token: String,
                    @Field("command") command: String,
                    @Field("barrier_id") barrier: Int): Observable<StateResponse>

    @FormUrlEncoded
    @POST("api.php")
    fun addBarrier(@Field("login") number: String,
                   @Field("key") token: String,
                   @Field("addBarrier") command: String,
                   @Field("tel_gsm") phone: String,
                   @Field("address") address: String,
                   @Field("user_info") name: String): Observable<StateResponse>

    @FormUrlEncoded
    @POST("api.php")
    fun updateBarrier(@Field("login") number: String,
                   @Field("key") token: String,
                   @Field("UpBarrier") command: String,
                   @Field("barrier_id") barrier: Int,
                   @Field("name") name: String,
                   @Field("point_x") pointX: Double,
                   @Field("point_y") pointY: Double,
                   @Field("address") address: String,
                   @Field("user_info") userInfo: String): Observable<StateResponse>

    @FormUrlEncoded
    @POST("api.php")
    fun removeBarrier(@Field("login") number: String,
                   @Field("key") token: String,
                   @Field("rmBarrier") barrier: Int): Observable<StateResponse>
}
