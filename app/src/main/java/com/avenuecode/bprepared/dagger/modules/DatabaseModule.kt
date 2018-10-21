package com.avenuecode.bprepared.dagger.modules

import android.arch.persistence.room.Room
import com.avenuecode.bprepared.BaseApp
import com.avenuecode.bprepared.database.DatabaseClient
import com.avenuecode.bprepared.database.daos.ItemDao
import com.avenuecode.bprepared.database.daos.UserDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: BaseApp): DatabaseClient {
        return Room.databaseBuilder(application, DatabaseClient::class.java, "bprepared-db")
                .build()
    }

    @Provides @Singleton
    fun provideUserDao(databaseClient: DatabaseClient): UserDao {
        return databaseClient.userDao()
    }

    @Provides @Singleton
    fun provideItemDao(databaseClient: DatabaseClient): ItemDao {
        return databaseClient.itemDao()
    }

}