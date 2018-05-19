package ru.techmas.barrier.models

import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers

data class AppData(
        var barrier: Barrier = Barrier(),
        var barriers: Barriers = Barriers(),
        var added: ArrayList<String> = ArrayList(),
        var photos: Photos = Photos()
)