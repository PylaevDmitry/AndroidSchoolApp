package ru.profsoft.testappschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Courses")
data class Course (

    @PrimaryKey
    val certNum:String,

    val courseName:String,

    val form:String,

    val period:String,

    val content:String,

    val teacher:String,

    val image:String
)