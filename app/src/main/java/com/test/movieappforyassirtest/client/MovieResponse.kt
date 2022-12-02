package com.test.movieappforyassirtest.client

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseMovie(

	@Json(name="original_language")
	val original_language: String? = null,

	@Json(name="imdb_id")
	val imdb_id: String? = null,

	@Json(name="video")
	val video: Boolean? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="backdrop_path")
	val backdrop_path: String? = null,

	@Json(name="revenue")
	val revenue: Int? = null,

	@Json(name="genres")
	val genres: List<GenresItem?>? = null,

	@Json(name="popularity")
	val popularity: Double? = null,

	@Json(name="production_countries")
	val production_countries: List<ProductionCountriesItem?>? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="vote_count")
	val vote_count: Int? = null,

	@Json(name="budget")
	val budget: Int? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="original_title")
	val original_title: String? = null,

	@Json(name="runtime")
	val runtime: Int? = null,

	@Json(name="poster_path")
	val poster_path: String? = null,

	@Json(name="spoken_languages")
	val spoken_languages: List<SpokenLanguagesItem?>? = null,

	@Json(name="production_companies")
	val production_companies: List<ProductionCompaniesItem?>? = null,

	@Json(name="release_date")
	val release_date: String? = null,

	@Json(name="vote_average")
	val vote_average: Double? = null,

	@Json(name="tagline")
	val tagline: String? = null,

	@Json(name="adult")
	val adult: Boolean? = null,

	@Json(name="homepage")
	val homepage: String? = null,

	@Json(name="status")
	val status: String? = null
) : Parcelable

@Parcelize
data class ProductionCountriesItem(

	@Json(name="iso_3166_1")
	val iso_3166_1: String? = null,

	@Json(name="name")
	val name: String? = null
) : Parcelable

@Parcelize
data class GenresItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class ProductionCompaniesItem(

	@Json(name="logo_path")
	val logo_path: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="origin_country")
	val origin_country: String? = null
) : Parcelable

@Parcelize
data class SpokenLanguagesItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="iso_639_1")
	val iso_639_1: String? = null,

	@Json(name="english_name")
	val english_name: String? = null
) : Parcelable
