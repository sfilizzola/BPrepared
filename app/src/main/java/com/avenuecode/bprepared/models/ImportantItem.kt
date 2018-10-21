package com.avenuecode.bprepared.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "items")
data class ImportantItem(@PrimaryKey
                         var id: Int,
                         var name: String,
                         var checked: Boolean,
                         var missing: Boolean)