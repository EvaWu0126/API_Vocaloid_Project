package com.example.apiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(val artistString: String,
                 val defaultName : String,
                 val id : String,
                 val lengthSeconds : Int,
                 val lyrics : Lyrics,
                 val name : String,
                 val publishDate: String,
                 val pvServices : String,
                 val ratingScore : Int, ) : Parcelable
