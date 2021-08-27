package com.tms.projectapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.schoolorgonizer.schedule.databaseLesson.DataDao
import com.example.schoolorgonizer.schedule.databaseLesson.DataEntity

@Database(entities = [DataEntity::class], version = 2)
abstract class ScheduleDataBase: RoomDatabase()  {

    abstract fun dataDao(): DataDao
}

object ScheduleDatabaseConstructor {
    fun create(context: Context): ScheduleDataBase =
        Room.databaseBuilder(
            context,
            ScheduleDataBase::class.java,
            "lesson_schedule"
        ).fallbackToDestructiveMigration().build()
}