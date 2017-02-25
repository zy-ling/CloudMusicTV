package com.vitoling.cloudmusictv.data

import com.vitoling.cloudmusictv.data.model.LoginResponse
import com.vitoling.cloudmusictv.data.model.user.PlaylistResponse
import com.vitoling.cloudmusictv.data.model.v3.playlist.PlaylistDetailResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 14:57.
 * Description:
 *
 */

private const val PARAMS = "params"
private const val ENCSECKEY = "encSecKey"

interface CloudMusicAPI {

    /**
     * 账号登录
     * */
    @POST("login")
    fun login(@Body body: RequestBody): Call<LoginResponse>

    /**
     * 手机号登录
     * */
    @POST("login/cellphone")
    fun login_cellphone(@Body body: RequestBody): Call<LoginResponse>

    @POST("feedback/weblog")
    fun feedback_weblog(@Body body: RequestBody): Call<Map<String, Any>>

    /**
     * 获取验证码
     * */
    @GET("/captcha?id={id}")
    fun captcha(@Path("id") id: String): Call<ResponseBody>

    /**
     * 获取用户的歌单
     * */
    @POST("user/playlist")
    fun user_playlist(@Body body: RequestBody): Call<PlaylistResponse>

    /**
     * 获取歌单的详情
     * */
    @POST("v3/playlist/detail")
    fun v3_playlist_detail(@Body body: RequestBody): Call<PlaylistDetailResponse>

    /**
     *
     * */
    @POST("user/getfollows/{id}")
    fun user_getfollows(@Path("id") id: Long, @Body body: RequestBody): Call<PlaylistResponse>


}