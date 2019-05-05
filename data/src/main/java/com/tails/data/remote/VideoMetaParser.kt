package com.tails.data.remote

import com.tails.domain.entities.VideoMeta
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.Charset
import java.util.regex.Pattern

object VideoMetaParser {

    const val USER_AGENT =
        "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36"

    private val patTitle = Pattern.compile("title=(.*?)(&|\\z)")
    private val patAuthor = Pattern.compile("author=(.+?)(&|\\z)")
    private val patChannelId = Pattern.compile("ucid=(.+?)(&|\\z)")
    private val patLength = Pattern.compile("length_seconds=(\\d+?)(&|\\z)")
    private val patViewCount = Pattern.compile("view_count=(\\d+?)(&|\\z)")
    private val patHlsvp = Pattern.compile("hlsvp=(.+?)(&|\\z)")

    val client: OkHttpClient = OkHttpClient.Builder().build()

    @Throws(UnsupportedEncodingException::class)
    fun parseVideoMeta(getVideoInfo: String, videoID: String): VideoMeta {
        var isLiveStream = false
        var title = ""
        var author = ""
        var channelId = ""
        var viewCount: Long = 0
        var length: Long = 0
        var mat = patTitle.matcher(getVideoInfo)

        if (mat.find()) {
            title = URLDecoder.decode(mat.group(1), "UTF-8")
        }

        mat = patHlsvp.matcher(getVideoInfo)
        if (mat.find()) {
            isLiveStream = true
        }

        mat = patAuthor.matcher(getVideoInfo)
        if (mat.find()) {
            author = URLDecoder.decode(mat.group(1), "UTF-8")
        }
        mat = patChannelId.matcher(getVideoInfo)
        if (mat.find()) {
            channelId = mat.group(1)
        }
        mat = patLength.matcher(getVideoInfo)
        if (mat.find()) {
            length = java.lang.Long.parseLong(mat.group(1))
        }
        mat = patViewCount.matcher(getVideoInfo)
        if (mat.find()) {
            viewCount = java.lang.Long.parseLong(mat.group(1))
        }

        return VideoMeta(videoID, title, author, channelId, length, viewCount, isLiveStream, getVideoInfo)
    }

    fun resBodyToStream(resBody: ResponseBody): String {
        val source = resBody.source().apply {
            request(Long.MAX_VALUE)
        }
        val buffer = source.buffer()
        val charset = Charset.forName("UTF-8")

        return buffer.readString(charset)
    }
}