package com.avenuecode.bprepared.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.avenuecode.bprepared.database.daos.ItemDao
import com.avenuecode.bprepared.database.daos.UserDao
import com.avenuecode.bprepared.models.ImportantItem
import com.avenuecode.bprepared.models.CurrentUser


@Database(entities = arrayOf(ImportantItem::class, CurrentUser::class), version = 1)
abstract class DatabaseClient : RoomDatabase() {
    abstract fun itemDao():ItemDao
    abstract fun userDao():UserDao
}