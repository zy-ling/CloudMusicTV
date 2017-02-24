package com.vitoling.cloudmusictv.api.model

/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 21:47.
 * Description:
 *
 */
data class Follower(
        var py: String,
        var time: Long,
        var playlistCount: Long,
        var followed: Long,
        var eventCount: Long,
        var userId: Long,
        var nickname: String,
        var follows: Long,
        var remarkName: String?,
        var expertTags: String?,
        var accountStatus: Int,
        var mutual: Boolean,
        var followeds: Long,
        var gender: Int,
        var userType: Int,
        var authStatus: Int,
        var avatarUrl: String?,
        var vipType: Int,
        var signature: String?,
        var blacklist: Boolean
                   )