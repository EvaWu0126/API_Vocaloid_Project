package com.example.apiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lyrics(val culcureCodes : List<String?>,
                  val id : Int,
                  val translationType : String?,
                  val url : String?,
                  val value : String) : Parcelable
