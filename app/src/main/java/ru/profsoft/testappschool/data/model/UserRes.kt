package ru.profsoft.testappschool.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRes(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
): Parcelable