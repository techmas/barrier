package ru.techmas.barrier.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.techmas.barrier.Const
import ru.techmas.barrier.api.endpoints.Barrier
import ru.techmas.barrier.api.endpoints.User

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

//class RestApi(private val tokenHelper: TokenHelper) {
class RestApi() {

    val user: User
    val barrier: Barrier
    private val retrofit: Retrofit

    init {

        val interceptor = HttpLoggingInterceptor()
//        val tokenInterceptor = TokenAppendingHeaderInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .addInterceptor(tokenInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()


        retrofit = Retrofit.Builder().baseUrl(Const.Url.API_PRODUCTION)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        user = retrofit.create(User::class.java)
        barrier = retrofit.create(Barrier::class.java)
    }

//    fun getServer(): String {
//
//        if (retrofit.baseUrl().toString()== Const.Url.API_PRODACTION)
//            return "Prodaction"
//        else
//            return "Test"
//    }


//    private inner class TokenAppendingHeaderInterceptor : Interceptor {
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//
//            val NO_AUTHORIZED = 401
//
//            val request = chain.request()
//            val token = tokenHelper.token
//            val newRequest = request.newBuilder()
//                    .addHeader(Const.Url.AUTHORIZATION, token)
//                    .build()
//
//            val response = chain.proceed(newRequest)
//            if (response.code() == NO_AUTHORIZED) {
//                // TODO: 25.04.2017  reload Application, and clear token or update token
//
//            }
//            return response
//        }
//    }

}
