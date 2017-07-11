package com.comix.kreader

/**
 * Created by junyizhang on 11/07/2017.
 */
object Constants {
    val NETWORK_CONNECTION_TIMEOUT = 30 // 30 sec
    val CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10 MB
    val CACHE_MAX_AGE = 2 // 2 min
    val CACHE_MAX_STALE = 7 // 7 day
    val NETWORK_RETRY_COUNT = 1
}