package com.example.task.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HighestAvgPriceVarietyGrade(
    val arrivalTotal: Int,
    val defaultGrade: Boolean,
    val defaultVariety: Boolean,
    val gradeDescr: String,
    val gradeID: String,
    val gradeName: String,
    val maxPrice: Int,
    val minPrice: Int,
    val varietyID: String,
    val varietyName: String
):Parcelable