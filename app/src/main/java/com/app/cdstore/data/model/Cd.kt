package com.app.cdstore.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

//@Entity(tableName = "cds")
@Serializable
data class Cd(
    val id_cd: Long,
    val titulo: String,
    val artista: String,
    val genero: String? = null,
    val ano_lancamento: Int? = null,
    val gravadora: String,
    val estoque: Int,
    val price: Double,
    val imageUrl: String,
    val descricao: String? = null

)