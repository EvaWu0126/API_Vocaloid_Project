package com.example.apiproject

import retrofit2.http.Query

import retrofit2.Call;
import retrofit2.http.GET;

interface VocaloidService {
    // where you list out the different endpoints in the API you want to call
    //function returns Call<type>, where type is the data returned in the json
    // in the @GE T("blah"). "blah" is the path to the file (endpoint)
    // https://vocadb.net/api/songs?songTypes=Original&afterDate=2020-01-01&childTags=false&unifyTypesAndTags=false&childVoicebanks=false&includeMembers=false&onlyWithPvs=false&status=Finished&start=0&maxResults=200&getTotalCount=false&sort=RatingScore&preferAccurateMatches=false&fields=MainPicture,Lyrics&lang=English

    @GET("songs")
    // query for all "tags", declare them in mainActivity
    fun getSongs(@Query("songTypes") songTypes: String,
                 @Query("afterDate") afterDate : String,
                 @Query("childTags") childTags : Boolean,
                 @Query("unifyTypesAndTags") unifyTypesAndTags : Boolean,
                 @Query("childVoicebanks") childVoicebanks : Boolean,
                 @Query("includeMembers") includeMembers : Boolean,
                 @Query("onlyWithPvs") onlyWithPvs : Boolean,
                 @Query("status") status : String,
                 @Query("start") start : Int,
                 @Query("maxResults") maxResults : Int,
                 @Query("getTotalCount") getTotalCount : Boolean,
                 @Query("sort") sort : String,
                 @Query("preferAccurateMatches") preferAccurateMatches : Boolean,
                 @Query("fields") fields: String,
                 @Query("lang") lang : String) : Call<ItemWrapper>
}

// val song = songEditText.text.toString()
// getSongs("Original", "Main"