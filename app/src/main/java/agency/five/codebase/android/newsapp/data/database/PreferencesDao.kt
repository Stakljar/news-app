package agency.five.codebase.android.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferencesDao {
    @Query("SELECT * FROM preferenceEnt")
    fun getAll(): Flow<List<PreferenceEnt>>

    @Query("SELECT * FROM preferenceEnt WHERE id = :id")
    fun getPreference(id: Int): PreferenceEnt

    @Query("SELECT * FROM preferenceEnt WHERE is_default = 1")
    fun getDefaultPreference(): Flow<PreferenceEnt?>

    @Query("DELETE FROM preferenceEnt WHERE id = :id")
    fun deletePreference(id: Int)

    @Query("UPDATE preferenceEnt SET is_default = 0 WHERE is_default = 1")
    fun removeDefaultPreference()

    @Query("UPDATE preferenceEnt SET is_default = 1 WHERE id = :id")
    fun addDefault(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPreference(preference: PreferenceEnt)

    @Update
    fun updatePreference(preference: PreferenceEnt)
}
