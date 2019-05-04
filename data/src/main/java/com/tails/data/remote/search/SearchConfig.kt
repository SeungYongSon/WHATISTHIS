package com.tails.data.remote.search

object SearchConfig{
    const val YOUTUBE_REGEX = "(?:[?&]vi?=|\\/embed\\/|\\/\\d\\d?\\/|\\/vi?\\/|https?:\\/\\/(?:www\\.)?youtu\\.be\\/)([A-Za-z0-9_\\-]{11})"

    const val YOUTUBE_API = "AIzaSyCm8Ij35766_QvZYVZX0E8gWeTY4M3s87c"
    const val YOUTUBE_SEARCH_LIST_TYPE = "video"
    const val YOUTUBE_SEARCH_LIST_PART = "id,snippet"
    const val YOUTUBE_SEARCH_LIST_FIELDS = "pageInfo,nextPageToken,items(id/videoId,snippet/title,snippet/thumbnails/default/url)"

    const val YOUTUBE_VIDEO_LIST_PART = "id,contentDetails,statistics"
    const val YOUTUBE_VIDEO_LIST_FIELDS = "items(contentDetails/duration,statistics/viewCount)"

    const val YOUTUBE_VIDEO_PART = "id,snippet,contentDetails,statistics"
    const val YOUTUBE_VIDEO_FIELDS = "items(id,snippet/title," + "snippet/thumbnails/default/url,contentDetails/duration,statistics/viewCount)"

    const val YOUTUBE_LANGUAGE_KEY = "hl"
    const val YOUTUBE_MAX_RESULTS = 50L
}