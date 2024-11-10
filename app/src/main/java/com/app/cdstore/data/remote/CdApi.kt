package com.app.cdstore.data.remote

import com.app.cdstore.data.model.Cd
import retrofit2.http.GET
import retrofit2.http.Path

interface CdApi {
    @GET("cds")
    suspend fun getCds(): List<Cd>

    @GET("cds/{id}")
    suspend fun getCdById(@Path("id") id: Long): Cd
}