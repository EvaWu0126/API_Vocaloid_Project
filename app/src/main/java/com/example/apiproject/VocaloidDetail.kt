package com.example.apiproject

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject.databinding.ActivityVocaloidDetailBinding
import android.net.Uri

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

        textViewOrigTitle.text = vocaloid?.defaultName
        textViewTransTitle.text = vocaloid?.name
        textViewArtist.text = vocaloid?.artistString
        textViewSongLength.text = toTime(vocaloid?.lengthSeconds)
        textViewPV.text = vocaloid?.pvServices
        textViewDate.text = vocaloid?.publishDate
        textViewRate.text = vocaloid?.ratingScore.toString()

        textViewLyrics.text = vocaloid?.lyrics?.get(0)?.value

        val uri: Uri = Uri.parse(vocaloid?.lyrics?.get(0)?.url)
        videoViewSong.setVideoURI(uri)


    }

    // converting from second to time :p in a stupid way but it works
    fun toTime(second : Int?): String{
        var a : String = (second?.div(60)).toString().substring(0,1)
        var b : String = (second?.div(60)).toString().substring(2,4)
        return "$a:$b"
    }


    // I do not know what is this, copy pasted from earthquake
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }

    }





}