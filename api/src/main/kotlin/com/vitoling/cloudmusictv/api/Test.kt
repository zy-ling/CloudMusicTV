package com.vitoling.cloudmusictv.api

import org.apache.http.Header
import org.apache.http.NameValuePair
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.cookie.Cookie
import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

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
//    val res = API.loginViaUsername("vitoling95@163.com", "ling371148667")
    val res = API.loginViaCellPhone("18970967366", "ling371148667")
    println(res)
//    hello(arrayOf())
}

fun hello(args: Array<String>) {
    val loginUrl = "http://music.163.com/weapi/login/cellphone"
    val username = "vitoling95@163.com"
    val phone = "18970967366"
    val password = "ling371148667"
    val encryptedPassword = EUtil.MD5Encrypt(password)
    val text = "{\"phone\": \"$phone\", \"rememberLogin\": \"true\", \"password\": \"$encryptedPassword\"}"
    val (params, encSecKey) = EUtil.encrypt(text)

    val headers = mutableListOf<BasicHeader>()
    headers.add(BasicHeader("Host", "music.163.com"))
    headers.add(BasicHeader("Referer", "http://music.163.com/"))
    headers.add(BasicHeader("Origin", "http://music.163.com"))
    headers.add(BasicHeader("Content-Type", "application/x-www-form-urlencoded"))
    headers.add(BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"))

    val data = fetchStandardData(loginUrl, listOf(), headers, listOf(BasicNameValuePair("params", params), BasicNameValuePair("encSecKey", encSecKey)))
    println(data)
}

private val fetchStandardData = fun(baseUrl: String, cookies: List<Cookie>, headers: List<Header>, params: List<NameValuePair>?): String? {
    val cookieStore = BasicCookieStore()
    cookieStore.addCookies(cookies.toTypedArray())
    val post = HttpPost(baseUrl)
    post.config = RequestConfig.custom().setSocketTimeout(3000).build()
    if (params != null) {
        post.entity = UrlEncodedFormEntity(params, "UTF-8")
    }
    val entity = HttpClients.custom().setDefaultHeaders(headers).setDefaultCookieStore(cookieStore).build().execute(post).entity
    return EntityUtils.toString(entity)
}
