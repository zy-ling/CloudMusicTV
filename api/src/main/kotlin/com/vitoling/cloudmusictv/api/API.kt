package com.vitoling.cloudmusictv.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
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
 */

object API {

    private const val BASE_API_URL = "http://music.163.com/weapi/"
    private const val HEADER_ORIGIN = "http://music.163.com"
    private const val HEADER_REFERER = "http://music.163.com/"
    private const val HEADER_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
    private const val HEADER_HOST = "music.163.com"
    private const val HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded"

    private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create()
    private val retrofit: Retrofit by lazy {

        // 设置通用请求头信息
        val headerInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    .header("Origin", HEADER_ORIGIN)
                    .header("Host", HEADER_HOST)
                    .header("Referer", HEADER_REFERER)
                    .header("User-Agent", HEADER_USER_AGENT)
                    .header("Content-Type", HEADER_CONTENT_TYPE)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        // 设置cookie
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        cookieManager.cookieStore.add(URI(BASE_API_URL), HttpCookie("appver", "1.5.0.75771").apply {
            domain = "http://music.163.com"
        })

        Retrofit.Builder().run {
            addConverterFactory(GsonConverterFactory.create(gson))
            baseUrl(BASE_API_URL)
            client(OkHttpClient.Builder().run {
                addInterceptor(headerInterceptor)
                cookieJar(JavaNetCookieJar(cookieManager))
                // build okhttp client
                build()
            })
            // build retrofit
            build()
        }
    }

    private val musicAPI: CloudMusicAPI = retrofit.create(CloudMusicAPI::class.java)

    fun loginViaUsername(username: String, password: String): Response<Map<String, Any>>? {

        val encryptedPassword = EUtil.MD5Encrypt(password)
        val usernameText = "{\"username\": \"$username\", \"rememberLogin\": \"true\", \"password\": \"$encryptedPassword\"}"
        val (params, encSecKey) = EUtil.encrypt(usernameText)
        val call = musicAPI.loginViaUsername(params = params, encSecKey = encSecKey)
        val res = call.execute()
        return res
    }

    fun loginViaCellPhone(phone: String, password: String): Response<Map<String, Any>>? {
        val encryptedPassword = EUtil.MD5Encrypt(password)
        val phoneText = "{\"phone\": \"$phone\", \"rememberLogin\": \"true\", \"password\": \"$encryptedPassword\"}"

        val (params, encSecKey) = EUtil.encrypt(phoneText)

        val call = musicAPI.loginViaCellPhone(params = params, encSecKey = encSecKey)
        val res = call.execute()
        return res
    }

    // 获取自己的歌单
    fun fetchMyPlayList(){

    }
}
