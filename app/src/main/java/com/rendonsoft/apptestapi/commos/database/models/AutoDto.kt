package com.rendonsoft.apptestapi.commos.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AutoItem
 */
@Entity(tableName = "Auto")
data class AutoDto(
        @PrimaryKey()
        @ColumnInfo val code: String,
        @ColumnInfo val name: String,
)
