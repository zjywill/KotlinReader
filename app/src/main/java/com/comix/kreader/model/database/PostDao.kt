package com.comix.kreader.model.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.comix.kreader.model.entity.Post
import io.reactivex.Flowable


/**
 * Created by junyizhang on 13/07/2017.
 */
@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getPosts(): Flowable<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(posts: Post)

    @Query("DELETE FROM post")
    fun deleteAllPosts()
}