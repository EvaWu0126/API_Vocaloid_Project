package com.example.apiproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.apiproject.databinding.ActivityVocaloidDetailBinding
import com.squareup.picasso.Picasso


class VocaloidDetail : AppCompatActivity() {

    private lateinit var binding: ActivityVocaloidDetailBinding
    val TAG = "VocaloidDetailView"
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
    private lateinit var imageViewVideo: ImageView
    private lateinit var checkBoxEnglish: CheckBox
    private lateinit var checkBoxRomaji: CheckBox
    private lateinit var layout: ConstraintLayout

    private lateinit var textViewNoVideo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocaloidDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_vocaloid_detail)
        // pass the putExtra to this class and get value
        val vocaloid = intent.getParcelableExtra<Item>(EXTRA_DETAIL)

        // check lyrics language
        fun checkLanguage(translate : Boolean): Int {
            for (i in vocaloid?.lyrics?.indices!!) {
                Log.d(TAG, "checkLanguage: $i")

                if(translate){ //if translate is true: english checked
                    Log.d(TAG, "checkLanguage: yes translate")
                    if (vocaloid.lyrics[i]?.translationType.equals("Translation")) {
                        Log.d(TAG, "checkLanguage: checked ${vocaloid.lyrics[i]?.cultureCodes?.contains("en")}}")
                        if(vocaloid.lyrics[i]?.cultureCodes?.contains("en") == true)
                        return i
                    }
                }else{ //if translate is false: romaji checked
                    if(vocaloid.lyrics[i]?.translationType.equals("Romanized")) {
                        Log.d(TAG, "checkLanguage: romanized")
                        return i
                    }
                }
            }
            return 0
        }

        //check video source
        fun checkSource(): String{
            for (i in vocaloid?.lyrics?.indices!!){
                if(vocaloid?.lyrics[i]?.source.equals("YouTube"))
                    return i.toString()
            }
            return "Video Not Available"
        }

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
        imageViewVideo = findViewById(R.id.imageView_video)
        checkBoxEnglish = findViewById(R.id.checkBox_main_english)
        checkBoxRomaji = findViewById(R.id.checkBox_main_romaji)

        layout = findViewById(R.id.layout_main_vocaDetail)

        textViewNoVideo = findViewById(R.id.textView_main_noVideo)
        textViewNoVideo.visibility = View.INVISIBLE

        textViewLyrics.movementMethod = ScrollingMovementMethod();

        textViewOrigTitle.text = vocaloid?.defaultName
        textViewTransTitle.text = vocaloid?.name
        textViewArtist.text = "Artist(s):    " + vocaloid?.artistString
        textViewSongLength.text = "Song Length:    " + toTime(vocaloid?.lengthSeconds)
        textViewPV.text = "PV Platforms: \n" + vocaloid?.pvServices
        textViewDate.text = "Published Date:    " + (vocaloid?.publishDate?.substring(0, 10) ?: " ")
        textViewRate.text = "Rating:    " + vocaloid?.ratingScore.toString()
        Picasso.get().load(vocaloid?.mainPicture?.urlOriginal).into(imageViewVideo)

        textViewLyrics.text = vocaloid?.lyrics?.get(0)?.value


        //videoview with url
        val sourceIndex: Int
        if(checkSource() != "Video Not Available"){
            Log.d(TAG, "checkSource: ${checkSource()}")
            sourceIndex = checkSource().toInt()
            val uri: Uri = Uri.parse(vocaloid?.lyrics?.get(sourceIndex)?.url)
            Log.d(TAG, "return uri: $uri")

            imageViewVideo.setOnClickListener(){
                val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse(vocaloid?.lyrics?.get(sourceIndex)?.url))
                val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(vocaloid?.lyrics?.get(sourceIndex)?.url))
                try {
                    this.startActivity(intentApp)
                } catch (ex: ActivityNotFoundException) {
                    this.startActivity(intentBrowser)
                }
            }

        }else{
            Log.d(TAG, "checkSource: not available")
            imageViewVideo.visibility = View.INVISIBLE
            textViewNoVideo.visibility = View.VISIBLE

        }


        buttonLyrics.setOnClickListener(){
            textViewArtist.visibility = View.INVISIBLE
            textViewSongLength.visibility = View.INVISIBLE
            textViewPV.visibility = View.INVISIBLE
            textViewDate.visibility = View.INVISIBLE
            textViewRate.visibility = View.INVISIBLE

            textViewLyrics.visibility = View.VISIBLE
            checkBoxEnglish.visibility = View.VISIBLE
            checkBoxRomaji.visibility = View.VISIBLE
        }

        buttonInfo.setOnClickListener(){
            textViewArtist.visibility = View.VISIBLE
            textViewSongLength.visibility = View.VISIBLE
            textViewPV.visibility = View.VISIBLE
            textViewDate.visibility = View.VISIBLE
            textViewRate.visibility = View.VISIBLE

            textViewLyrics.visibility = View.INVISIBLE
            checkBoxEnglish.visibility = View.INVISIBLE
            checkBoxRomaji.visibility = View.INVISIBLE
        }

        checkBoxEnglish.setOnClickListener(){
            if(checkBoxEnglish.isChecked == true){
                checkBoxRomaji.isChecked = false
                var langCode : Int = checkLanguage( true)
                textViewLyrics.text = vocaloid?.lyrics?.get(langCode)?.value
            }else{
                textViewLyrics.text = vocaloid?.lyrics?.get(0)?.value
            }
        }

        checkBoxRomaji.setOnClickListener(){
            if(checkBoxRomaji.isChecked == true){
                checkBoxEnglish.isChecked = false
                var langCode : Int = checkLanguage(false)
                textViewLyrics.text = vocaloid?.lyrics?.get(langCode)?.value
            }else{
                textViewLyrics.text = vocaloid?.lyrics?.get(0)?.value
            }
        }
    }


    fun toTime(second : Int?): String{
        var a : String = (second?.div(60)).toString()
        var b : String = (second?.rem(60)).toString()
        return "$a:$b"
    }








}