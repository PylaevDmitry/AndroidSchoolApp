package ru.profsoft.testappschool.data.model.db

import com.google.gson.annotations.SerializedName

data class CourseRequest (
    @SerializedName ("certNum")
    val certNum:String,
    @SerializedName ("courseName")
    val courseName:String,
    @SerializedName ("form")
    val form:String,
    @SerializedName ("period")
    val period:String,
    @SerializedName ("content")
    val content:String,
    @SerializedName ("teacher")
    val teacher:String,
    @SerializedName ("image")
    val image:String
)