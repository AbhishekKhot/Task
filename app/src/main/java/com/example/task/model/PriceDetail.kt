package com.example.task.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceDetail(
    val _id: String,
    val arrivalTotal: Int,
    val defaultGrade: Boolean,
    val defaultVariety: Boolean,
    val gradeDescr: String,
    val gradeID: String,
    val gradeName: String,
    val maxPrice: Int,
    val minPrice: Int,
    val varietyID: String,
    val varietyName: String,
) : Parcelable