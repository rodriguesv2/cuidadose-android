package br.com.rodroid.cuidadose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rodroid.cuidadose.data.local.dao.MedicationDao
import br.com.rodroid.cuidadose.data.local.entity.MedicationEntity

/**
 * Main Room database for the app.
 *
 * To add a new entity:
 * 1. Create the @Entity data class in data/local/entity/
 * 2. Create the @Dao interface in data/local/dao/
 * 3. Add the entity class to the [entities] array below
 * 4. Add the DAO abstract getter here
 * 5. Bump [version] and provide a [androidx.room.migration.Migration]
 */
@Database(
    entities = [MedicationEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicationDao(): MedicationDao
}
