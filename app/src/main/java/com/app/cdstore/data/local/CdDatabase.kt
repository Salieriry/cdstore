// data/local/CdDatabase.kt
package com.app.cdstore.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.cdstore.data.model.Cd

@Database(entities = [Cd::class], version = 1)
abstract class CdDatabase : RoomDatabase() {
    abstract fun cdDao(): CdDao

    companion object {
        private var INSTANCE: CdDatabase? = null

        fun getDatabase(context: Context): CdDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CdDatabase::class.java,
                    "cd_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}