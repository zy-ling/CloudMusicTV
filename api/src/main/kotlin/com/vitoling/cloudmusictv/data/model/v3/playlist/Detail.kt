package com.vitoling.cloudmusictv.data.model.v3.playlist

import com.vitoling.cloudmusictv.data.model.common.User


/**
 * Created by lingzhiyuan.
 * Date : 2/25/17.
 * Time : 19:38.
 * Description:
 *
 */

data class PlaylistDetailResponse(
        var playlist: PlaylistDetail,
        //      var previleges: List<>,
        var code: Int
                                 )

data class PlaylistDetail(
        var subscribers: List<Any>,
        var subscribed: Boolean,
        var creator: User,
        var tracks: List<Track>,
        var trackIds: List<TrackId>,
        var tags: List<String>,
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

data class TrackId(
        var id: Long,
        var v: Long
                  )