package com.example.task.model

import android.os.Parcel
import android.os.Parcelable

data class Translations(
    val en: String,
    val hi: String,
    val mr: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(en)
        parcel.writeString(hi)
        parcel.writeString(mr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Translations> {
        override fun createFromParcel(parcel: Parcel): Translations {
            return Translations(parcel)
        }

        override fun newArray(size: Int): Array<Translations?> {
            return arrayOfNulls(size)
        }
    }
}