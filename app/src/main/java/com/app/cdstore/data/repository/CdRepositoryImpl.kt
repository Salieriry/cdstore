// data/repository/CdRepositoryImpl.kt
package com.app.cdstore.data.repository

import com.app.cdstore.data.model.Cd
import com.app.cdstore.data.remote.CdApi
import com.app.cdstore.data.local.CdDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


/*
@Singleton
class CdRepositoryImpl @Inject constructor(
    private val api: CdApi,
    private val database: CdDatabase
) : CdRepository {

    override suspend fun getCds(): List<Cd> = withContext(Dispatchers.IO) {
        try {
            // Primeiro tenta obter do cache local
            var cds = database.cdDao().getAllCds()

            // Se o cache estiver vazio, busca da API
            if (cds.isEmpty()) {
                val remoteCds = api.getCds()
                database.cdDao().insertAll(remoteCds)
                cds = remoteCds
            }

            cds
        } catch (e: Exception) {
            // Em caso de erro, retorna dados do cache se disponível
            database.cdDao().getAllCds()
        }
    }

    override suspend fun getCdById(id: Long): Cd? = withContext(Dispatchers.IO) {
        try {
            // Primeiro tenta obter do cache local
            var cd = database.cdDao().getCdById(id)

            // Se não encontrar no cache, busca da API
            if (cd == null) {
                cd = api.getCdById(id)
                cd?.let { database.cdDao().insert(it) }
            }

            cd
        } catch (e: Exception) {
            // Em caso de erro, retorna dados do cache se disponível
            database.cdDao().getCdById(id)
        }
    }

    override suspend fun searchCds(query: String): List<Cd> = withContext(Dispatchers.IO) {
        try {
            database.cdDao().searchCds("%$query%")
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBestSellers(): List<Cd> = withContext(Dispatchers.IO) {
        try {
            database.cdDao().getBestSellers()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getNewAdditions(): List<Cd> = withContext(Dispatchers.IO) {
        try {
            database.cdDao().getNewAdditions()
        } catch (e: Exception) {
            emptyList()
        }
    }
} */