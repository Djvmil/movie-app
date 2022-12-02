package com.test.movieappforyassirtest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.test.movieappforyassirtest.R
import com.test.movieappforyassirtest.base.BaseFragment
import com.test.movieappforyassirtest.client.ApiList
import com.test.movieappforyassirtest.client.RetrofitClient
import com.test.movieappforyassirtest.databinding.FragmentMovieBinding
import com.test.movieappforyassirtest.utils.Constants
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieFragment : BaseFragment() {

    lateinit var binding: FragmentMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.showProgressDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments?.containsKey("movieId")!!){
            arguments?.getLong("movieId")?.let { getMovieByIdWs(it) }
        }
    }



    fun getMovieByIdWs(movieId: Long){
        lifecycleScope.launchWhenCreated {
            val apiList = RetrofitClient.getInstance().create(ApiList::class.java)
            activity.hideProgressDialog()
            try {
                val result = apiList.getMovieById(movieId)
                if (result != null && result.isSuccessful){
                    Log.d(TAG, "Result Success:\n"
                            + "id = ${result.body()?.id}"
                            + " | original_title = ${result.body()?.original_title}"
                            + " | budget = ${result.body()?.budget}")


                    binding.movieTitle.text = result.body()?.title
                    binding.movieDesc.text = result.body()?.overview

                    var genres = ""
                    result.body()?.genres?.forEach { genres += "${it?.name} - "}

                    if (genres.trim().endsWith('-'))
                        genres = genres.dropLast(2)

                    binding.movieGenre.text = genres

                    Glide.with(activity)
                        .load(Constants.BASE_URL_IMG+result.body()?.backdrop_path)
                        .centerCrop()
                        .into(binding.movieImg);

                    Log.d(TAG, "Result Success movieList: ${result.body()}")


                }else{
                    Log.d(TAG, "Result Error: ${result.code()}"+result.errorBody().toString())

                }

            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).visibleRowBack()
    }

    override fun onBackPress(): Boolean {
        Log.d(TAG, "onBackPress: ${super.onBackPress()}" )
        (activity as MainActivity).loadFragment(HomeFragment())
        return super.onBackPress()
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}