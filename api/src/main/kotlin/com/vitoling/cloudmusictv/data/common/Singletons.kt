package com.vitoling.cloudmusictv.data.common

import com.vitoling.cloudmusictv.data.model.common.User

object CurrentUser {

    var avatarUrl: String = ""
    var avatarId: Long = 0L
    var username: String = ""
    var phone: String = ""
    var user: User? = null

}