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
    @Query("SELECT * FROM Post")
    fun getPosts(): Flowable<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Query("DELETE FROM Post")
    fun deleteAllPosts()
}