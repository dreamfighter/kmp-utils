package id.dreamfighter.kmp.utils

class JvmTimeUtil: TimeUtil {
    override val currentTimeMillis: Long = System.currentTimeMillis()
}

actual fun getTimeUtil(): TimeUtil = JvmTimeUtil()