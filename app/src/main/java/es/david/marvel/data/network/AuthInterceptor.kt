package es.david.marvel.data.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.and
import java.security.MessageDigest


class AuthInterceptor : Interceptor {

    private val KEY_PRIVATE = "bf04fa66fdc8eed9d53392125f277d300efbb192"
    private val KEY_PUBLIC = "6a2c4e85d5f0a665c493386f87292344"

    val TIME_STAMP_KEY = "ts"
    val HASH_KEY = "hash"
    val API_KEY = "apikey"


    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(TIME_STAMP_KEY, getTimeStamp())
            .addQueryParameter(API_KEY, KEY_PUBLIC)
            .addQueryParameter(HASH_KEY, getMd5Key())
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getMd5Key(): String {
        val input: String = getTimeStamp() + KEY_PRIVATE + KEY_PUBLIC
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        val md5Bytes: ByteArray = md.digest(input.toByteArray())
        val md5 = StringBuilder()
        for (i in md5Bytes.indices) {
            md5.append(Integer.toHexString(md5Bytes[i] and 0xFF or 0x100).substring(1, 3))
        }

        return md5.toString()
    }

    private fun getTimeStamp(): String {
        return System.currentTimeMillis().toString()
    }

}