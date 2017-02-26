package com.vitoling.cloudmusictv.data.service

import com.google.gson.Gson
import com.vitoling.cloudmusictv.data.EUtil
import com.vitoling.cloudmusictv.data.model.LoginResponse
import com.vitoling.cloudmusictv.data.model.user.PlaylistResponse
import com.vitoling.cloudmusictv.data.model.v3.playlist.PlaylistDetailResponse
import com.vitoling.cloudmusictv.data.source.CloudMusicAPI
import okhttp3.FormBody
import okhttp3.RequestBody
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

    private val basicFormBodyBuilder: (String, String) -> RequestBody = {
        params, encSecKey ->
        FormBody.Builder().add(PARAMS, params).add(ENC_SEC_KEY, encSecKey).build()
    }

    private data class PARAMS_UL(
            var username: String,
            var password: String,
            var rememberLogin: Boolean
                                )

    val loginViaUsername: (String, String) -> Response<LoginResponse> = {
        username, password ->
        val loginParams = PARAMS_UL(username, EUtil.MD5Encrypt(password), true)
        val jsonParams = Gson().toJson(loginParams)
        val (params, encSecKey) = EUtil.encrypt(jsonParams)
        val formBody = basicFormBodyBuilder(params, encSecKey)
        val call = musicAPI.login(formBody)
        // 返回Response对象
        call.execute()
    }

    private data class PARAMS_CL(
            var phone: String,
            var password: String,
            var rememberLogin: Boolean
                                )

    val loginViaCellPhone: (String, String) -> Response<LoginResponse> = {
        phone, password ->
        val loginParams = PARAMS_CL(phone, EUtil.MD5Encrypt(password), true)
        val jsonParams = Gson().toJson(loginParams)
        val (params, encSecKey) = EUtil.encrypt(jsonParams)
        val formBody = basicFormBodyBuilder(params, encSecKey)
        val call = musicAPI.login_cellphone(formBody)
        // 返回Response对象
        call.execute()
    }

    private data class PARAMS_MP(
            var offset: Long,
            var limit: Long,
            var uid: Long
                                )

    // 获取自己的歌单
    val fetchMyPlaylist: (Long, Long, Long) -> Response<PlaylistResponse> = {
        offset, limit, uid ->
        val p = PARAMS_MP(offset, limit, uid)
        val jsonParams: String?
        jsonParams = Gson().toJson(p)
        val (params, encSecKey) = EUtil.encrypt(jsonParams)
        val formBody = basicFormBodyBuilder(params, encSecKey)
        val call = musicAPI.user_playlist(formBody)
        // 返回Response对象
        call.execute()
    }

    private data class PARAMS_PD(
            var id: Long = 0,
            var offset: Long = 0,
            var total: Boolean = true,
            var limit: Long = 1001,
            var n: Long = 1000
                        )

    // 获取歌单详情
    val fetchPlaylistDetail: (Long, Long, Boolean, Long, Long) -> Response<PlaylistDetailResponse> = {
        id, offset, total, limit, n ->
        val p = PARAMS_PD(id, offset, total, limit, n)
        val jsonParams: String?
        jsonParams = Gson().toJson(p)
        val (params, encSecKey) = EUtil.encrypt(jsonParams)
        val formBody = basicFormBodyBuilder(params, encSecKey)
        val call = musicAPI.v3_playlist_detail(formBody)
        // 返回Response对象
        call.execute()
    }
}
