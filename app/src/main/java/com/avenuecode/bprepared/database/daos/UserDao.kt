package com.avenuecode.bprepared.database.daos

import android.arch.persistence.room.*
import com.avenuecode.bprepared.models.CurrentUser
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE name = :name")
    fun getUser(name:String): Single<CurrentUser>

    @Transaction
    @Insert
    fun insert(currentUser: CurrentUser)

    @Delete
    fun delete(currentUser: CurrentUser)
}