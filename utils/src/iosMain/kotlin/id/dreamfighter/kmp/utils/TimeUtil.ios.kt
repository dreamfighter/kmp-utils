package id.dreamfighter.kmp.utils

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import platform.UIKit.UIDevice

class IOSTimeUtil: TimeUtil {
    override val currentTimeMillis: Long = NSDate().timeIntervalSince1970.toLong() * 1000
}

actual fun getTimeUtil(): TimeUtil = IOSTimeUtil()