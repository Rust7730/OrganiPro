package com.e243768.examen1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e243768.examen1.data.dao.UserDao
import com.e243768.examen1.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}