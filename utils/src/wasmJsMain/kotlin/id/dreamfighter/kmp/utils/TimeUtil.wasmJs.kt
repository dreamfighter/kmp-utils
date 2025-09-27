package id.dreamfighter.kmp.utils

class WasmTimeUtil: TimeUtil {
    override val currentTimeMillis: Long = 0
}

actual fun getTimeUtil(): TimeUtil = WasmTimeUtil()