package com.rendonsoft.apptestapi.commos.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rendonsoft.apptestapi.commos.database.dao.AutoDao
import com.rendonsoft.apptestapi.commos.database.models.AutoDto

/**
 * AppDataBase
 */
@Database(entities = [AutoDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun autoDao(): AutoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_test_api_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}