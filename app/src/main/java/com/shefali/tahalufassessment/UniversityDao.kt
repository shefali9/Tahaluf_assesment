package com.shefali.tahalufassessment

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Dao
interface UniversityDao {
    @Query("SELECT * FROM universities")
    fun getAllUniversities(): List<University>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<University>)
}

@Database(entities = [University::class], version = 1)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "university_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
