package com.vitoling.cloudmusictv.data.model.common

/**
 * Created by lingzhiyuan.
 * Date : 2/25/17.
 * Time : 19:49.
 * Description:
 *
 */

data class User(
        var defaultAvatar: Boolean,
        var province: Long,
        var authStatus: Int,
        var followed: Boolean,
        var avatarUrl: String?,
        var accountStatus: Int,
        var gender: Int,
        var city: Long,
        var birthday: Long,
        var userId: Long,
        var userType: Int,
        var nickname: String?,
        var signature: String?,
        var description: String?,
        var detailDescription: String?,
        var avatarImgId: Long,
        var backgroundImgId: Long,
        var backgroundUrl: String?,
        var authority: Int,
        var mutual: Boolean,
        var expertTags: List<String>,
        var djStatus: Int,
        var vipType: Int,
        var remarkName: String?,
        var avatarImgIdStr: String?,
        var backgroundImgIdStr: String?,
        var avatarImgId_str: String?
                  )