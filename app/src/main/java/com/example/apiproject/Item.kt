package com.example.apiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val artistString: String,
                val defaultName : String,
                val id : String,
                val lengthSeconds : Int,
                val lyrics : List<Lyrics>,
                val mainPicture: Picture,
                val name : String,
                val publishDate: String,
                val pvServices : String,
                val ratingScore : Int, ) : Parcelable
