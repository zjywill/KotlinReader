package com.comix.kreader.model.interactors

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.comix.kreader.model.database.LocalDatabase
import com.comix.kreader.model.database.PostDao
import com.comix.kreader.model.entity.Post
import com.comix.kreader.utils.Loge
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by zhangjunyi on 23/10/2017.
 */
@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    lateinit var postDao: PostDao
    lateinit var database: LocalDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java).allowMainThreadQueries().build()
        postDao = database.getPostDao()
        Loge.d("setup postDao: " + postDao)
    }

    @After
    fun tearDown() {
        database.close()
        Loge.d("tearDown")
    }

    @Test
    fun testInsertedAndRetrievedPostsMatch() {
        val posts = listOf(mockPost(2), mockPost(1))
        postDao.insertPosts(posts)

        Loge.d("testInsertedAndRetrievedPostsMatch posts size: " + posts.size)

        postDao.getPosts().test().assertNoValues()
        var allPosts = postDao.allPosts()
        Assert.assertEquals(posts, allPosts)

        postDao.insertPost(mockPost(1))
        postDao.insertPost(mockPost(2))

        allPosts = postDao.allPosts()
        Assert.assertEquals(posts, allPosts)

        postDao.insertPost(mockPost(3))
        allPosts = postDao.allPosts()
        Assert.assertEquals(3, allPosts.size)
    }

    @Test
    fun testDeletePosts() {
        postDao.deleteAllPosts()
        val allPosts = postDao.allPosts()
        Assert.assertEquals(0, allPosts.size)
    }

    fun mockPost(id: Long): Post {
        val post = Post(id)
        post.name = id.toString()
        post.content = id.toString()
        post.postdate = id.toString()
        return post
    }


}