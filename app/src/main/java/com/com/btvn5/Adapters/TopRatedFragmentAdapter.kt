package com.com.btvn5.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.com.btvn5.Activities.DetailActivity
import com.com.btvn5.POKO.TopRatedPOKO
import com.com.btvn5.R
import com.com.btvn5.Utils.EndPoints
import com.com.btvn5.Utils.SquareLayout
import com.squareup.picasso.Picasso

/**
 * Created by Rosinante24 on 26/10/17.
 */
class TopRatedFragmentAdapter : RecyclerView.Adapter<TopRatedFragmentAdapter.ViewHolder> {
    var mMovieData: List<TopRatedPOKO.TopRatedData>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity?, data: List<TopRatedPOKO.TopRatedData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val topRatedData: TopRatedPOKO.TopRatedData = mMovieData!!.get(position)

        Picasso.with(mContext)
                .load(EndPoints.IMAGE_URL_POSTER + topRatedData.poster_path)
                .placeholder(R.drawable.placeholder)
                .into(holder!!.movieThumb)

        holder.squareLayout.setOnClickListener({ v ->
            val intent = Intent(mContext?.applicationContext, DetailActivity::class.java)
            intent.putExtra("id_movie", topRatedData.idMovie)
            intent.putExtra("title_movie", topRatedData.movieTitle)
            intent.putExtra("backdrop_movie", topRatedData.backdrop_path)
            intent.putExtra("overview_movie", topRatedData.overview)
            intent.putExtra("releasedate_movie", topRatedData.release_date)
            intent.putExtra("votesaverage_movie", topRatedData.vote_average)
            intent.putExtra("votecount_movie", topRatedData.vote_count)
            mContext!!.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.toprated_thumbnail, parent, false)

        return ViewHolder(inflater)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var movieThumb = itemView?.findViewById<View>(R.id.upcomingThumbnail) as ImageView
        var squareLayout = itemView?.findViewById<View>(R.id.sq_Upcoming) as SquareLayout
    }

}