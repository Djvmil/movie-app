package com.test.movieappforyassirtest.client

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * @Author Moustapha S. Dieme ( Djvmil_ ) on 1/12/22.
 */
@Parcelize
data class ErrorResponse(

	@Json(name="status_message")
	val statusMessage: String? = null,

	@Json(name="status_code")
	val statusCode: Int? = null,

	@Json(name="success")
	val success: Boolean? = null
) : Parcelable
