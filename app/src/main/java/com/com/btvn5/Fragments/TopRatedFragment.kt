package com.com.btvn5.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.com.btvn5.Adapters.TopRatedFragmentAdapter
import com.com.btvn5.POKO.TopRatedPOKO
import com.com.btvn5.R
import com.com.btvn5.Utils.EndPoints
import com.com.btvn5.Utils.InitRetrofit
import kotlinx.android.synthetic.main.fragment_toprated.*
import retrofit2.Call
import retrofit2.Callback


/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_toprated, container, false)

        var swipe = view.findViewById<View>(R.id.refresh_toprated) as SwipeRefreshLayout
        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            getTopRatedData()
        }

        getTopRatedData()
        return view
    }

    private fun getTopRatedData() {

        var api = InitRetrofit().getInitInstance()
        var call = api.request_toprated(EndPoints.API_KEY)
        call.enqueue(object : Callback<TopRatedPOKO> {

            override fun onFailure(call: Call<TopRatedPOKO>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<TopRatedPOKO>?, response: retrofit2.Response<TopRatedPOKO>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        var data = response.body()?.data
                        val adapter = TopRatedFragmentAdapter(activity, data)
                        recyler_toprated.adapter = adapter
                        recyler_toprated.layoutManager = GridLayoutManager(activity, 2)
                    }
                }
            }

        })

    }

}
