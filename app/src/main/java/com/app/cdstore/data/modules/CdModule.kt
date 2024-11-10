package com.app.cdstore.data.modules


import com.app.cdstore.data.local.CdDatabase
import com.app.cdstore.data.remote.CdApi
import com.app.cdstore.data.repository.CdRepository
//import com.app.cdstore.data.repository.CdRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CdModule {

 /*   @Provides
    fun provideCdApi(): CdApi {
        // Retorne a instância do CdApi (pode ser Retrofit ou qualquer outra implementação)

    } */

   /* @Provides
    fun provideCdDatabase(): CdDatabase {
        // Retorne a instância do banco de dados local (ex: Room)
    }

    @Provides
    fun provideCdRepository(api: CdApi, database: CdDatabase): CdRepository {
        // Retorne a implementação do CdRepository
        return CdRepositoryImpl(api, database)
    } */

}