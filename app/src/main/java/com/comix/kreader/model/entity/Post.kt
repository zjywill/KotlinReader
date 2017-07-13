package com.comix.kreader.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by junyizhang on 12/07/2017.
 */
@Entity(tableName = "post")
data class Post(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        @Expose
        var id: Long,
        @ColumnInfo(name = "content")
        @SerializedName("content")
        @Expose
        var content: String,
        @ColumnInfo(name = "postdate")
        @SerializedName("postdate")
        @Expose
        var postdate: String,
        @ColumnInfo(name = "name")
        @SerializedName("name")
        @Expose
        var name: String,
        @ColumnInfo(name = "topic")
        @SerializedName("topic")
        @Expose
        var topic: String,
        @ColumnInfo(name = "image_url")
        @SerializedName("image_url")
        @Expose
        var imageUrl: String,
        @ColumnInfo(name = "link")
        @SerializedName("link")
        @Expose
        var link: String
)