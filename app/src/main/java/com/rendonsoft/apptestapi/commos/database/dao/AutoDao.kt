package com.rendonsoft.apptestapi.commos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rendonsoft.apptestapi.commos.database.models.AutoDto

/**
 * AutoDao
 */
@Dao
interface AutoDao {
    @Query("SELECT * FROM Auto")
    fun getAutos(): List<AutoDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuto(vararg autos: AutoDto)

    @Query("DELETE FROM Auto")
    fun deleteAutos()
}