package com.test.movieappforyassirtest.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
interface ApiList {
    @GET("/3/movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId: Long
    ) : Response<ResponseMovie>

    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("page") page: Int = 1) : Response<MovieListResponse>
}