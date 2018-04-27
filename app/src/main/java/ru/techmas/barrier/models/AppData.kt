package ru.techmas.barrier.models

import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers

data class AppData(
        var barrier: Barrier = Barrier(),
        var barriers: Barriers = Barriers(),
        var photos: Photos = Photos()
)