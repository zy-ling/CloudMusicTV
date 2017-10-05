package com.vitoling.cloudmusictv.data.model.v3.song

data class SongDetailResponse(var code: Int, var songs: List<Song>, var privileges: List<Any>)

 /**
  * h, m, l : bit rate
  * */

data class Song(

        var name: String, var id: Long, var pst: Int, var t: Int, var ar: List<Any>, var alia: List<Any>, var pop: Float, var st: Int, var rt: Any?, var fee: Double, var v: Int, var crbt: Any?, var cf: String, var al: Any, var dt: Int, var h: Any, var m: Any, var l: Any, var a: Any?, var cd: String, var no: Int, var rtUrl: String?, var ftype: Int, var rtUrls: List<String>, var djId: Long, var copyright: Int, var s_id: Long, var mst: Int, var cp: Int, var mv: Long, var rtype: Int, var rUrl: String?)
