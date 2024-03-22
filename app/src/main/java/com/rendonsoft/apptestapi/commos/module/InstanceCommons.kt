package com.rendonsoft.apptestapi.commos.module

import androidx.room.Room
import com.rendonsoft.apptestapi.commos.database.database.AppDatabase
import com.rendonsoft.apptestapi.commos.network.Network
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val instanceCommons = module {

    single {
        Network()
    }

    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java, "app_test_api_database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
}