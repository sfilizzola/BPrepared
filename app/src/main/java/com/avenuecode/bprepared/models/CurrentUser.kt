package com.avenuecode.bprepared.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "users")
data class CurrentUser(@PrimaryKey
                var id: Int,
                       var name: String,
                       var email:String,
                       var sex:Char,
                       var age:Int,
                       var city:String,
                       var children:Int,
                       var reducedMobility:Boolean,
                       var carOwner:Boolean,
                       var weigth:Int,
                       var height:Double,
                       var practiceSports:Boolean,
                       var smoker:Boolean,
                       var drinker:Boolean,
                       var yearIncome:Float,
                       var currentJob:String,
                       var mainHobby:String,
                       var motherLanguage:String,
                       var petOwner:Boolean,
                       var profileFilled:Boolean = false)
