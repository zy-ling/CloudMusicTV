package com.vitoling.cloudmusictv.data.model

/**
 * Created by lingzhiyuan.
 * Date : 2/25/17.
 * Time : 16:20.
 * Description:
 *
 */

data class Binding(
        var userId: Long,
        var url: String?,
        var tokenJsonStr: String?,
        var expiresIn: Long,
        var refreshTime: Long,
        var expired: Boolean,
        var id: Long,
        var type: Int
                  )

data class Profile(
        var userId: Long,
        var accountStatus: Int,
        var nickname: String?,
        var expertTags: List<String>,
        var avatarImgId: Long,
        var backgroundImgId: Long,
        var userType: Int,
        var vipType: Int,
        var authStatus: Int,
        var remarkName: String?,
        var mutual: Boolean,
        var backgroundUrl: String?,
        var defaultAvatar: Boolean,
        var avatarUrl: String?,
        var gender: Int,
        var birthday: Long,
        var city: Long,
        var followed: Boolean,
        var detailDescription: String?,
        var djStatus: Int,
        var province: Long,
        var description: String?,
        var avatarImgIdStr: String?,
        var backgroundImgIdStr: String?,
        var signature: String?,
        var authority: Int,
        var avatarImgId_str: String?
                  )

data class Account(
        var id: Long,
        var userName: String?,
        var type: Int,
        var status: Int,
        var whitelistAuthority: Int,
        var createTime: Long,
        var salt: String?,
        var tokenVersion: Int,
        var ban: Int,
        var baoyueVersion: Int,
        var donateVersion: Int,
        var vipType: Int,
        var viptypeVersion: Long,
        var anonimousUser: Boolean
                  )

data class LoginResponse(
        var loginType: Int,
        var code: Int,
        var account: Account?,
        var profile: Profile?,
        var bindings: List<Binding>?
                )