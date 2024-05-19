package dev.nowoczesny.aop.opengym.data

import android.app.Application
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by jaroslawmichalik on 12/05/2024
 */
class DatabaseModule(applicationContext: Application) {
    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "open-gyms-db"
    ).build()
}

@Database(entities = [GymDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gymDao(): GymDao
}

@Dao
interface GymDao {

    @Query("SELECT * FROM gymdbentity WHERE name LIKE '%' || :query || '%' OR address LIKE '%' || :query || '%'")
    suspend fun searchByQuery(query: String): List<GymDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gyms: List<GymDbEntity>)

    @Query("SELECT * FROM gymdbentity WHERE id == :id LIMIT 1")
    fun findById(id: String): GymDbEntity

}

@Entity
data class GymDbEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val address: String,
    val category: String,
    @Embedded
    val location: LocationDbEntity,
    val imageUrl: String
)

@Entity
data class LocationDbEntity(
    val latitude: Double,
    val longitude: Double
)