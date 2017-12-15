package com.vitoling.cloudmusictv.data.model.song.enhance.player

// /song/enhance/player
data class SongUrlResponse(var data: List<Data>, var code: Int)

data class Data(var id: Long, var url: String, var br: Long, var size: Long, var md5: String, var code: Int, var expi: Long, var type: String, var gain: Float, var fee: Double, var uf: Any?, var payed: Int, var flag: Int, var canExtend: Boolean)