package com.example.apiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lyrics(val cultureCodes : List<String>,
                  val source : String?,
                  val translationType : String?,
                  val url : String,
                  val value : String) : Parcelable
