package ru.techmas.barrier.api.models

import com.google.gson.annotations.SerializedName

/*
{
    "id": "423",
    "address": null,
    "number": "74959881776",
    "user_info": "Демо объект",
    "old": null
  }
 */

data class Barrier(
    var id: Int,
    var address: String,
    var number: String,
    @SerializedName("user_info")
    var name: String,
    var old: String
)