package com.vitoling.cloudmusictv.data

import com.google.gson.Gson
import com.vitoling.cloudmusictv.data.service.CloudMusicService

/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 20:11.
 * Description:
 *
 */
class Test {
}

fun main(args: Array<String>) {
//    val res = CloudMusicService.login("vitoling95@163.com", "ling371148667", true)
//    val res = CloudMusicService.login("18970967366", "ling371148667", false)
//    val res = CloudMusicService.fetchMyPlaylist(0, 1001, 17952620)
    val res = CloudMusicService.fetchPlaylistDetail(14660449, 0, true, 1000, 1000)
    println(Gson().toJson(res.body()))
}