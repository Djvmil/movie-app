package com.test.movieappforyassirtest.client

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
@Parcelize
data class MovieListResponse(

	@Json(name="page")
	val page: Int? = null,

	@Json(name="total_pages")
	val total_pages: Int? = null,

	@Json(name="results")
	val results: ArrayList<ResultsItem>? = null,

	@Json(name="total_results")
	val total_results: Int? = null
) : Parcelable

@Parcelize
data class ResultsItem(

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="original_language")
	val original_language: String? = null,

	@Json(name="original_title")
	val original_title: String? = null,

	@Json(name="video")
	val video: Boolean? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="genre_ids")
	val genre_ids: List<Int?>? = null,

	@Json(name="poster_path")
	val poster_path: String? = null,

	@Json(name="backdrop_path")
	val backdrop_path: String? = null,

	@Json(name="release_date")
	val release_date: String? = null,

	@Json(name="popularity")
	val popularity: Double? = null,

	@Json(name="vote_average")
	val vote_average: Double? = null,

	@Json(name="id")
	val id: Long? = null,

	@Json(name="adult")
	val adult: Boolean? = null,

	@Json(name="vote_count")
	val vote_count: Int? = null
) : Parcelable
