package com.app.cdstore.data.repository

import com.app.cdstore.data.model.Cd
import kotlinx.coroutines.flow.Flow


interface CdRepository {
    suspend fun getCds(): List<Cd>
    suspend fun getCdById(id: Long): Cd?
    suspend fun searchCds(query: String): List<Cd>
    suspend fun getBestSellers(): List<Cd>
    suspend fun getNewAdditions(): List<Cd>
}

