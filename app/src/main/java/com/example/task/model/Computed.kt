package com.example.task.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Computed(
    val highestAvgPriceVarietyGrade: HighestAvgPriceVarietyGrade
):Parcelable