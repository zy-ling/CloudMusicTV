package com.vitoling.cloudmusictv.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 14:57.
 * Description:
 *
 */


interface CloudMusicAPI {

    @POST("login")
    @FormUrlEncoded
    fun loginViaUsername(@Field("params") params: String, @Field("encSecKey") encSecKey: String): Call<Map<String,Any>>

    @POST("login/cellphone")
    @FormUrlEncoded
    fun loginViaCellPhone(@Field("params") params: String, @Field("encSecKey") encSecKey: String): Call<Map<String,Any>>

    @POST("feedback/weblog")
    @FormUrlEncoded
    fun weblog(@Field("params") params: String, @Field("encSecKey") encSecKey: String): Call<Map<String,Any>>

    // http://music.163.com/captcha?id=jttyONCA3hyzVp91sctTEuz2
    @GET("/captcha?id={id}")
    fun captcha(@Path("id") id: String): Call<ResponseBody>
}