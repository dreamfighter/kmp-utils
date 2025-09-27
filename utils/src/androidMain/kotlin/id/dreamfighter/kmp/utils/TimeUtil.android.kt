package id.dreamfighter.kmp.utils

class AndroidTimeUtil: TimeUtil {
    override val currentTimeMillis: Long = System.currentTimeMillis()
}

actual fun getTimeUtil(): TimeUtil = AndroidTimeUtil()