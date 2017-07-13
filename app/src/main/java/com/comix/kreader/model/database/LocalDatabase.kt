package com.comix.kreader.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.comix.kreader.model.entity.Post


/**
 * Created by junyizhang on 13/07/2017.
 */
@Database(entities = arrayOf(Post::class), version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getPostDao(): PostDao
}