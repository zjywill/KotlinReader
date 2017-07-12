package com.comix.kreader.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by junyizhang on 12/07/2017.
 */
class Post(
        @SerializedName("id")
        @Expose
        var id: Long,
        @SerializedName("content")
        @Expose
        var content: String,
        @SerializedName("postdate")
        @Expose
        var postdate: String,
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("topic")
        @Expose
        var topic: String,
        @SerializedName("image_url")
        @Expose
        var imageUrl: String,
        @SerializedName("link")
        @Expose
        var link: String
)