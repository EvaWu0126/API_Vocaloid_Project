package com.example.apiproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.databinding.ActivityVocaloidDetailBinding

class VocaloidDetail : AppCompatActivity() {

    private lateinit var binding: ActivityVocaloidDetailBinding
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private val language = "default"
    companion object {
        val EXTRA_DETAIL = "detail"
    }

    private lateinit var textViewOrigTitle: TextView
    private lateinit var textViewTransTitle: TextView
    private lateinit var textViewArtist: TextView
    private lateinit var textViewSongLength: TextView
    private lateinit var textViewPV: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewRate: TextView
    private lateinit var textViewLyrics: TextView
    private lateinit var buttonInfo: Button
    private lateinit var buttonLyrics: Button
    private lateinit var videoViewSong: VideoView
    private lateinit var checkBoxEnglish: CheckBox
    private lateinit var checkBoxRomaji: CheckBox
    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocaloidDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_vocaloid_detail)
        // pass the putExtra to this class and get value
        val vocaloid = intent.getParcelableExtra<Item>(EXTRA_DETAIL)

        textViewOrigTitle = findViewById(R.id.textView_main_origTitle)
        textViewTransTitle = findViewById(R.id.textView_main_transTitle)
        textViewArtist = findViewById(R.id.textView_main_artist)
        textViewSongLength = findViewById(R.id.textView_main_songDuration)
        textViewPV = findViewById(R.id.textView_main_platform)
        textViewDate = findViewById(R.id.textView_main_publishDate)
        textViewRate = findViewById(R.id.textView_main_rate)
        textViewLyrics = findViewById(R.id.textView_main_songLyrics)
        buttonInfo = findViewById(R.id.button_main_vocoDetail)
        buttonLyrics = findViewById(R.id.button_main_vocoLyrics)
        videoViewSong = findViewById(R.id.videoView_main_video)
        checkBoxEnglish = findViewById(R.id.checkBox_main_english)
        checkBoxRomaji = findViewById(R.id.checkBox_main_romaji)

        layout = findViewById(R.id.layout_main_vocaDetail)

//        textViewOrigTitle.text = vocaloid?.defaultName?: null
//
//        textViewLyrics.setVisibility(View.VISIBLE)
//        if(language == "default"){
//            textViewLyrics.text = vocaloid?.lyrics?.value?.
//        }




    }



}