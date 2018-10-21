package com.avenuecode.bprepared.database.daos

import android.arch.persistence.room.*
import com.avenuecode.bprepared.models.ImportantItem
import io.reactivex.Single

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): Single<List<ImportantItem>>

    @Transaction
    @Insert
    fun insertAll(items:List<ImportantItem>)

    @Transaction
    @Insert
    fun insert(item:ImportantItem)

    @Delete
    fun delete(item: ImportantItem)
}