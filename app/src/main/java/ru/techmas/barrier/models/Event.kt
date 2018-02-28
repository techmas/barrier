package ru.techmas.barrier.models

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class Event {

    var id: Int = 0
        private set

    var data: Any? = null
    var secondData: Any? = null

    constructor(type: Int) {
        this.id = type
    }


    constructor(type: Int, data: Any) {
        this.id = type
        this.data = data
    }

    constructor(type: Int, data: Any, secondData: Any) {
        this.id = type
        this.data = data
        this.secondData = secondData
    }
}
