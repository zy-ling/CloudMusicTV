package com.vitoling.cloudmusictv.data

/**
 * Created by lingzhiyuan.
 * Date : 2/24/17.
 * Time : 15:52.
 * Description:
 */

import org.apache.commons.lang3.RandomStringUtils
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EUtil {

    private val modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7" +
                          "b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280" +
                          "104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932" +
                          "575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b" +
                          "3ece0462db0a22b8e7"
    private val nonce = "0CoJUm6Qyw8W8jud"
    private val pubKey = "010001"

    val encrypt: (String) -> Pair<String, String> = {
        val secKey = RandomStringUtils.random(16, "0123456789abcde")
        val encText = aesEncrypt(aesEncrypt(it, nonce), secKey)
        val encSecKey = rsaEncrypt(secKey, pubKey, modulus)
        (encText to encSecKey)
    }

    private val aesEncrypt: (String, String) -> String = {
        text, key ->
        try {
            val iv = IvParameterSpec("0102030405060708".toByteArray(charset("UTF-8")))
            val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
            val encrypted = cipher.doFinal(text.toByteArray())
            Base64.getEncoder().encodeToString(encrypted)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    }

    val MD5Encrypt: (String) -> String = {
        try {
            val digest = MessageDigest.getInstance("md5")
            val bs = digest.digest(it.toByteArray())
            var hexString = ""
            bs.asSequence().map { it.toInt() and 255 }.forEach {
                hexString += if (it in 0 .. 15) {
                    ("0" + Integer.toHexString(it))
                } else {
                    Integer.toHexString(it)
                }
            }
            hexString
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            ""
        }
    }

    private val rsaEncrypt: (String, String, String) -> String = {
        text, pubKey, modulus ->
        val reversedText = StringBuilder(text).reverse().toString()
        val rs = BigInteger(String.format("%x", BigInteger(1, reversedText.toByteArray())), 16)
                .modPow(BigInteger(pubKey, 16), BigInteger(modulus, 16))
        var r = rs.toString(16)
        if (r.length >= 256) {
            r.substring(r.length - 256, r.length)
        } else {
            while (r.length < 256) {
                r = 0.toString() + r
            }
            r
        }
    }

}