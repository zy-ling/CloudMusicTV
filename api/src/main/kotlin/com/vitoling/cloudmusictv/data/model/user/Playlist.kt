package com.vitoling.cloudmusictv.data.model.user

import com.vitoling.cloudmusictv.data.model.common.User

/**
 * Created by lingzhiyuan.
 * Date : 2/25/17.
 * Time : 15:52.
 * Description:
 *
 * 用户创建和收藏的歌单模板类
 *
 */

data class PlaylistResponse(
        var more: Boolean,
        var playlist: List<Playlist>?,
        var code: Int
                           )

data class Playlist(
        var subscribers: List<Any>,
        var subscribed: Boolean,
        var creator: User?,
        var artists: Any?,
        var tracks: Any?,
        var tags: List<String>?,
        var createTime: Long,
        var playCount: Long,
        var ordered: Boolean,
        var highQuality: Boolean,
        var coverImgId: Long,
        var totalDuration: Long,
        var userId: Long,
        var anonimous: Boolean,
        var specialType: Int,
        var updateTime: Long,
        var commentThreadId: String?,
        var trackCount: Long,
        var trackUpdateTime: Long,
        var newImported: Boolean,
        var privacy: Int,
        var coverImgUrl: String?,
        var description: String?,
        var status: Int,
        var subscribedCount: Long,
        var cloudTrackCount: Long,
        var adType: Int,
        var trackNumberUpdateTime: Long,
        var name: String?,
        var id: Long
                   )


