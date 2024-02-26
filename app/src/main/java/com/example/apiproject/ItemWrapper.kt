package com.example.apiproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemWrapper(val items: List<Item>) : Parcelable
