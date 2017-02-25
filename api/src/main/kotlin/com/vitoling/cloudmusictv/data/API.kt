package com.vitoling.cloudmusictv.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vitoling.cloudmusictv.data.model.LoginResponse
import com.vitoling.cloudmusictv.data.model.user.PlaylistResponse
import com.vitoling.cloudmusictv.data.model.v3.playlist.PlaylistDetailResponse
import okhttp3.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.HttpCookie
import java.net.URI


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

object API {

    private const val PARAMS = "params"
    private const val ENCSECKEY = "encSecKey"

    private const val COOKIE_DOMAIN = "http://music.163.com"
    private const val BASE_API_URL = "http://music.163.com/weapi/"
    private const val HEADER_ORIGIN = "Origin"
    private const val HEADER_ORIGIN_VALUE = "http://music.163.com"
    private const val HEADER_REFERER = "Referer"
    private const val HEADER_REFERER_VALUE = "http://music.163.com/"
    private const val HEADER_USER_AGENT = "User-Agent"
    private const val HEADER_USER_AGENT_VALUE = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
    private const val HEADER_HOST = "Host"
    private const val HEADER_HOST_VALUE = "music.163.com"
    private const val HEADER_CONTENT_TYPE = "Content-Type"
    //    private const val HEADER_CONTENT_TYPE_VALUE = "application/json;charset=UTF-8"
    private const val HEADER_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded"
    private const val COOKIE_APP_VER = "appver"
    private const val COOKIE_APP_VER_VALUE = "1.5.2"

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create()
        // 设置通用请求头信息
        val headerInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    .header(HEADER_ORIGIN, HEADER_ORIGIN_VALUE)
                    .header(HEADER_HOST, HEADER_HOST_VALUE)
                    .header(HEADER_REFERER, HEADER_REFERER_VALUE)
                    .header(HEADER_USER_AGENT, HEADER_USER_AGENT_VALUE)
                    .header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        // 设置cookie
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        cookieManager.cookieStore.add(URI(BASE_API_URL), HttpCookie(COOKIE_APP_VER, COOKIE_APP_VER_VALUE).apply {
            domain = COOKIE_DOMAIN
        })

        // Build retrofit
        Retrofit.Builder().run {
            addConverterFactory(GsonConverterFactory.create(gson))
            baseUrl(BASE_API_URL)
            client(OkHttpClient.Builder().run {
                addInterceptor(headerInterceptor)
                cookieJar(JavaNetCookieJar(cookieManager))
                // build okhttp client
                build()
            })
            build()
        }
    }

    private val musicAPI: CloudMusicAPI = retrofit.create(CloudMusicAPI::class.java)

    private val basicFormBodyBuilder: (String, String) -> RequestBody = {
        params, encSecKey ->
        FormBody.Builder().add(PARAMS, params).add(ENCSECKEY, encSecKey).build()
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
            var id: Long,
            var offset: Long,
            var total: Boolean,
            var limit: Long,
            var n: Long
                        )

    // 获取自己的歌单
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
