package ru.techmas.barrier

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class Const {

    object Time {
        val REPEAT_SMS = 60
        val OPEN_DELAY: Long = 5
    }

    object Url {
        val API_PRODUCTION = "https://api.privratnik.net:44590/app/"
        val API_TEST = "http://admin.ss-ss.sssss.ru/ss/ss/"
        val AUTHORIZATION = "Authorization"
    }

    object ActivityRequest {
        val ADD_BARRIER = 1001
    }

    object State {
        val OK = 1
        val ERROR = 0
    }
}
