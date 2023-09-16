package com.nvvi9.spoticloud.util

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val spotiCloudDispatcher: SpotiCloudDispatchers)

enum class SpotiCloudDispatchers {
    Default, IO
}