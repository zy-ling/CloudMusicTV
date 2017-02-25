package com.vitoling.cloudmusictv.data.common.enums

/**
 * Created by lingzhiyuan.
 * Date : 2017/1/10.
 * Time : 17:31.
 * Description:
 *
 */
enum class Gender(val code: Short, val alias: Array<String>) {
    UNKNOWN(- 1, arrayOf("未知", "unknown")),
    MALE(1, arrayOf("男", "male")),
    FEMALE(2, arrayOf("女", "female"));

    companion object{
        fun fromCode(code: Short): Gender {
            values().forEach {
                if (it.code == code) {
                    return it
                }
            }
            return UNKNOWN
        }
    }
}