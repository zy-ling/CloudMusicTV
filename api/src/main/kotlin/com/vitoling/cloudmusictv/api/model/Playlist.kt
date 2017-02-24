package com.vitoling.cloudmusictv.api.model

/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 21:52.
 * Description:
 *
 */

data class Playlist(
        var subscribers: List<Any>,
        var subscribed: Boolean,
        var creator: Creator,
        var tracks: List<Any>,
        var trackIds: List<Any>,
        var tags: List<Any>,
        var adType: Int,
        var trackNumberUpdateTime: Long,
        var subscribedCount: Long,
        var status: Int,
        var coverImgId: Long,
        var trackCount: Long,
        var createTime: Long,
        var playCount: Long,
        var userId: Long,
        var specialType: Int,
        var updateTime: Long,
        var commentThreadId: String,
        var trackUpdateTime: Long,
        var privacy: Int,
        var newImported: Boolean,
        var highQuality: Boolean,
        var cloudTrackCount: Long,
        var ordered: Boolean,
        var description: String?,
        var name: String,
        var id: Long,
        var shareCount: Long,
        var commentCount: Long
                   )

data class Creator(
        var defaultAvatar: Boolean,
        var province: Int?,
        var authStatus: Int,
        var followed: Boolean,
        var avatarUrl: String?,
        var accountStatus: Int,
        var gender: Int,
        var city: Int?,
        var birthday: Long?,
        var userId: Long,
        var userType: Int,
        var nickname: String?,
        var signature: String?,
        var description: String?,
        var detailDescription: String?,
        var avatarImgId: Long?,
        var backgroundImgId: Long,
        var backgroundUrl: String?,
        var authority: Int?,
        var mutual: Boolean,
        var expertTags: Any,
        var djStatus: Int,
        var vipType: Int,
        var remarkName: String?,
        var avatarImgIdStr: Long?,
        var backgroundImgIdStr: Long?,
        var avatarImgId_str: Long?
                  )

data class Track(
        var name: String,
        var id: Long,
        var pst: Long,
        var t: Long,
        var ar: List<Map<String, Any>>,
        var alia: List<Any>,
        var pop: Double,
        var st: Long,
        var rt: String?,
        var fee: Long,
        var v: Long,
        var crbt: String?,
        var cf: String?,
        var al: Map<String, Any>,
        var dt: Long,
        var h: Map<String, Any>,
        var m: Map<String, Any>,
        var l: Map<String, Any>,
        var a: String?,
        var cd: String?,
        var no: Long,
        var rtUrl: String?,
        var ftype: Long,
        var rtUrls: List<Any>,
        var rtype: Long?,
        var rurl: String?,
        var mst: Long,
        var cp: Long,
        var mv: Long
                )