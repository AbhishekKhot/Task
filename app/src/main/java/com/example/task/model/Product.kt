package com.example.task.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val cmdtyID: String,
    val cmdtySegment: String,
    val cmdtyStdName: String,
    val picUrl: String,
    val posts: List<Post>,
    val sortOrder: Int,
    val translations: Translations,
) : Parcelable
