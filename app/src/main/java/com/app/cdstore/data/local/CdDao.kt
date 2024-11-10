// data/local/CdDao.kt
package com.app.cdstore.data.local

import androidx.room.*
import com.app.cdstore.data.model.Cd

@Dao
interface CdDao {
    @Query("SELECT * FROM cds")
    suspend fun getAllCds(): List<Cd>

    @Query("SELECT * FROM cds WHERE id = :id")
    suspend fun getCdById(id: Long): Cd?

    @Query("SELECT * FROM cds WHERE title LIKE :query OR artist LIKE :query")
    suspend fun searchCds(query: String): List<Cd>

    @Query("SELECT * FROM cds ORDER BY popularity DESC LIMIT 10")
    suspend fun getBestSellers(): List<Cd>

    @Query("SELECT * FROM cds ORDER BY releaseDate DESC LIMIT 10")
    suspend fun getNewAdditions(): List<Cd>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cd: Cd)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cds: List<Cd>)

    @Delete
    suspend fun delete(cd: Cd)
}