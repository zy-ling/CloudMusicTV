package com.vitoling.cloudmusictv.data.service

import com.google.gson.Gson
import com.vitoling.cloudmusictv.data.EUtil
import com.vitoling.cloudmusictv.data.model.LoginResponse
import com.vitoling.cloudmusictv.data.model.song.enhance.player.SongUrlResponse
import com.vitoling.cloudmusictv.data.model.user.PlaylistResponse
import com.vitoling.cloudmusictv.data.model.v3.playlist.PlaylistDetailResponse
import com.vitoling.cloudmusictv.data.model.v3.song.SongDetailResponse
import com.vitoling.cloudmusictv.data.source.CloudMusicAPI
import okhttp3.FormBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 15:45.
 * Description:
 *
 * 网易云音乐相关API
 *
 * 登录API的密码需要先经过MD5加密再调用encrypt()
 *
 */

object CloudMusicService {

    private const val PARAMS = "params"
    private const val ENC_SEC_KEY = "encSecKey"

    private val musicAPI: CloudMusicAPI = CloudMusicAPI.CLOUD_MUSIC_API_ADAPTER.create(CloudMusicAPI::class.java)

    private fun getRequest(cParams: Map<String, Any>): RequestBody{
        val jsonParams = Gson().toJson(cParams)
        val (params, encSecKey) = EUtil.encrypt(jsonParams)
        return FormBody.Builder().add(PARAMS, params).add(ENC_SEC_KEY, encSecKey).build()
    }

    internal fun loginViaCellphone(phone: String, password: String): Response<LoginResponse> {
        val loginParams = mapOf("phone" to phone, "password" to EUtil.MD5Encrypt(password), "rememberLogin" to true.toString())
        val formBody = getRequest(loginParams)
        val call = musicAPI.login_cellphone(formBody)
        return call.execute()
    }

    /**
     * not working due to yidun
     * */
    internal fun loginViaUsername(username: String, password: String): Response<Any> {

        val params = mapOf("username" to username, "password" to EUtil.MD5Encrypt(password), "rememberLogin" to true, "clientToken" to "1_E+ypitxL1PuC0dQsbWpFSbfoJrl6OzKN_JQJaQgkv32AtvYSndelHB4UMOzOTuWty_BxTl/MPFvyHLdF4KmnGaSw==")
        val formBody = getRequest(params)
        val call = musicAPI.login(formBody)
        return call.execute()
    }

    internal fun fetchPlaylistDetail(id: Long, offset: Long, total: Boolean, limit: Long, n: Long): Response<PlaylistDetailResponse> {

        val params = mapOf("id" to id, "offset" to offset, "total" to total, "limit" to limit, "n" to n)
        val formBody = getRequest(params)
        val call = musicAPI.v3_playlist_detail(formBody)
        return call.execute()
    }

    internal fun fetchMyPlaylist(offset: Long = 0, limit: Long = 1001, uid: Long): Response<PlaylistResponse> {

        val params = mapOf("offset" to offset, "limit" to limit, "uid" to uid)
        val formBody = getRequest(params)
        val call = musicAPI.user_playlist(formBody)
        return call.execute()
    }

    internal fun fetchSongDetail(id: Long): Response<SongDetailResponse>{
        val params = mapOf("c" to "[{\"id\":$id}]", "ids" to "[$id]")
        val formBody = getRequest(params)
        val call = musicAPI.v3_song_detail(formBody)
        return call.execute()
    }

    /**
     * br is bit rate
     * */
    internal fun fetchSongUrl(id: Long, br: Long = 999000): Response<SongUrlResponse>{
        val params = mapOf("br" to br, "ids" to "[$id]")
        val formBody = getRequest(params)
        val call = musicAPI.song_enhance_player_url(formBody)
        return call.execute()
    }
}

