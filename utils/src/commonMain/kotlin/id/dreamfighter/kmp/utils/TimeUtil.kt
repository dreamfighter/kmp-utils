package id.dreamfighter.kmp.utils

interface TimeUtil {
    val currentTimeMillis: Long
}

expect fun getTimeUtil(): TimeUtil