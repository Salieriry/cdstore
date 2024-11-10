package com.app.cdstore.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cds")
data class Cd(
    @PrimaryKey val id: Long,
    val titulo: String,
    val artista: String,
    val genero: String? = null,
    val ano_lancamento: Int? = null,
    val gravadora: String,
    val estoque: Int,
    val price: Double,
    val imageUrl: String,
    val descricao: String? = null,

)