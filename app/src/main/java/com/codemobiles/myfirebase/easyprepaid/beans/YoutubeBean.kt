package com.codemobiles.myfirebase.test.beans

data class YoutubeBean(
    val error: Boolean,
    val error_msg: String,
    val youtubes: List<Youtube>
)

data class Youtube(
    val avatar_image: String,
    val id: String,
    val subtitle: String,
    val title: String,
    val youtube_image: String
)