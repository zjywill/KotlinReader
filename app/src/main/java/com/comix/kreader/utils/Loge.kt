package com.comix.kreader.utils

import android.util.Log
import com.comix.kreader.BuildConfig

/**
 * Created by junyizhang on 14/07/2017.
 */
class Loge {
    companion object Factory {

        val LOG_STATUS: Boolean = BuildConfig.DEBUG
        val LOG_TAG: String = "JUNYI"

        fun getFunctionName(): String {
            val sts = Thread.currentThread().stackTrace
            for (stsElement in sts) {
                if (!stsElement.isNativeMethod && stsElement.className != Thread::class.java.name
                        && stsElement.className != this.javaClass.name) {
                    return "*" + stsElement.fileName + ":" + stsElement.lineNumber + "/" + stsElement.methodName + "*"
                }
            }
            return ""
        }


        fun v(info: String) {
            if (LOG_STATUS) {
                val tag = getFunctionName()
                Log.v(LOG_TAG, "[$tag]: $info")
            }
        }

        fun i(info: String) {
            if (LOG_STATUS) {
                val tag = getFunctionName()
                Log.i(LOG_TAG, "[$tag]: $info")
            }
        }

        fun d(info: String) {
            if (LOG_STATUS) {
                val tag = getFunctionName()
                Log.d(LOG_TAG, "[$tag]: $info")
            }
        }

        fun w(info: String) {
            if (LOG_STATUS) {
                val tag = getFunctionName()
                Log.w(LOG_TAG, "[$tag]: $info")
            }
        }

        fun e(info: String) {
            if (LOG_STATUS) {
                val tag = getFunctionName()
                Log.e(LOG_TAG, "[$tag]: $info")
            }
        }


    }
}