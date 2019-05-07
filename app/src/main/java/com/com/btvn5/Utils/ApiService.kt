package com.com.btvn5.Utils

import com.com.btvn5.POKO.DetaiPOKO
import com.com.btvn5.POKO.NowPlayingPOKO
import com.com.btvn5.POKO.TrailersPOKO
import com.com.btvn5.POKO.TopRatedPOKO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rosinante24 on 26/10/17.
 */
interface ApiService {

    @GET("now_playing")
    fun request_nowPlaying(
            @Query("api_key") apiKey: String
    ): Call<NowPlayingPOKO>

    @GET("top_rated")
    fun request_toprated(
            @Query("api_key") apiKey: String
    ): Call<TopRatedPOKO>

    @GET("{detail_id}")
    fun request_detail(
            @Path("detail_id") movieId: Int,
            @Query("api_key") apiKey: String
    ): Call<DetaiPOKO>


    @GET("{trailer_id}/videos")
    fun request_trailer(
            @Path("trailer_id") trailerId: Int,
            @Query("api_key") apiKey: String
    ): Call<TrailersPOKO>


}