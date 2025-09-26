package id.dreamfighter.kmp.utils

import id.dreamfighter.kmp.swift.Utils
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual fun Double.toCurrency(code: String): String {
    return Utils.formatNumberWithCode(code,this)
}