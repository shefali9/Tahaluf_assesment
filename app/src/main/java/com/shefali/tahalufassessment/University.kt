package com.shefali.tahalufassessment

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "universities")
@Parcelize
data class University(
    @PrimaryKey val name: String,
    val country: String,
    @SerializedName("state-province")
    val state_province: String?,

    val alpha_two_code: String?,
    val web_pages: List<String>?,
) : Parcelable
