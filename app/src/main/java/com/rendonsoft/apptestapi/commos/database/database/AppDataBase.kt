package com.rendonsoft.apptestapi.commos.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rendonsoft.apptestapi.commos.database.dao.AutoDao
import com.rendonsoft.apptestapi.commos.database.models.AutoDto

/**
 * AppDataBase
 */
@Database(entities = [AutoDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun autoDao(): AutoDao
}