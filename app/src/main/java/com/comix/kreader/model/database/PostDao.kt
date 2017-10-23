package com.comix.kreader.model.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.comix.kreader.model.entity.Post
import io.reactivex.Flowable


@Dao
interface PostDao {
    @Query("SELECT * FROM post ORDER BY postdate DESC")
    fun allPosts(): List<Post>

    @Query("SELECT * FROM post ORDER BY postdate DESC")
    fun getPosts(): Flowable<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(posts: Post)

    @Query("DELETE FROM post")
    fun deleteAllPosts()
}