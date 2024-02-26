package com.example.apiproject

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class VocaloidAdapter (var vocaloidList: List<Item>) : RecyclerView.Adapter<VocaloidAdapter.ViewHolder>()  {

    val TAG = "VocaloidAdapter"

    //binding
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewArtist : TextView
        val textViewDate : TextView
        val textViewRating : TextView
        val imageViewThumbnail : ImageView
        val layout: ConstraintLayout


        init {
            // Define click listener for the ViewHolder's View
            textViewName = view.findViewById(R.id.textView_vocaloidItem_defaultName)
            textViewArtist = view.findViewById(R.id.textView_vocaloidItem_artist)
            textViewDate = view.findViewById(R.id.textView_vocaloidItem_publishDate)
            textViewRating = view.findViewById(R.id.textView_vocaloidItem_rating)
            imageViewThumbnail = view.findViewById(R.id.imageView_vocaloidItem_thumbNail)
            layout = view.findViewById(R.id.layout_vocaloidItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    // pass down data from json
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = vocaloidList[position]
        holder.textViewName.text = data.defaultName
        holder.textViewArtist.text = data.artistString
        holder.textViewRating.text = data.ratingScore.toString()
        Log.d(TAG, "vocaloidlist running")

    }

    override fun getItemCount(): Int {
        return vocaloidList.size
    }

}