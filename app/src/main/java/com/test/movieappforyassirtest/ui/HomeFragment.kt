package com.test.movieappforyassirtest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.movieappforyassirtest.R
import com.test.movieappforyassirtest.base.BaseFragment
import com.test.movieappforyassirtest.client.ApiList
import com.test.movieappforyassirtest.client.MoviePaginationAdapter
import com.test.movieappforyassirtest.client.ResultsItem
import com.test.movieappforyassirtest.client.RetrofitClient
import com.test.movieappforyassirtest.databinding.FragmentHomeBinding
import com.test.movieappforyassirtest.databinding.FragmentMovieBinding
import com.test.movieappforyassirtest.utils.Constants
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var movieAdpter: MoviePaginationAdapter
    lateinit var movieFragment: MovieFragment

    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    var page = 1
    var movieList: ArrayList<ResultsItem> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        activity.showProgressDialog()
        setupRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieWs()
    }

    fun getMovieWs(){
        lifecycleScope.launchWhenCreated {
            val apiList = RetrofitClient.getInstance().create(ApiList::class.java)
            hideProgressBar()
            activity.hideProgressDialog()
            try {
                val result = apiList.getMovies(page)
                if (result != null && result.isSuccessful){
                    Log.d(TAG, "Result Success:\n"
                                + "page = ${result.body()?.page}"
                                + " | totalPages = ${result.body()?.total_pages}"
                                + " | totalResults = ${result.body()?.total_results}")

                    result.body()?.results?.let { movieList.addAll(it) }
                    movieAdpter?.differ?.submitList(movieList)
                    isLastPage = page == result.body()?.total_results
                    page++

                    Log.d(TAG, "Result Success movieList: $movieList")

                    movieAdpter.notifyDataSetChanged()

                }else{
                    Log.d(TAG, "Result Error: ${result.code()}"+result.errorBody().toString())

                }

            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        Log.e(TAG, "showProgressBar: ")
        isLoading = true
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount


            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.LIMIT

            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if(shouldPaginate) {
                showProgressBar()
                getMovieWs()
                isScrolling = false
            } else {
                binding.movieRecyclerView.setPadding(0, 0, 0, 0)
            }
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupRecyclerView() {

        Log.e(TAG, "setupRecyclerView: ")
        movieAdpter = MoviePaginationAdapter(
            layout = R.layout.movie_item,
            differCallback = object : DiffUtil.ItemCallback<ResultsItem>() {
                override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                    return oldItem == newItem
                }
            },
            onBind = { view, movie, position ->
                movie as ResultsItem

                view.movie_name.text = movie.title
                view.movie_desc.text = movie.overview
                view.vote_count.text = movie.vote_count.toString()

                Glide.with(activity)
                    .load(Constants.BASE_URL_IMG+movie.poster_path)
                    .centerCrop()
                    .into(view.movie_img);
            },
            onItemClickListener = { view, movie, position ->
                movie as ResultsItem
                var fragment = MovieFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putLong("movieId", movie.id!!)
                (activity as MainActivity).loadFragment(fragment)

            }
        )


        binding.movieRecyclerView.apply {
            adapter = movieAdpter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(scrollListener)
        }

    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).goneRowBack()
    }

    override fun onBackPress(): Boolean {
        Log.d(TAG, "onBackPress: ${super.onBackPress()}" )
        return false
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}