package agency.five.codebase.android.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PreferenceEnt::class], version = 1)
@TypeConverters(Converters::class)
abstract class PreferencesDatabase : RoomDatabase() {
    abstract fun getDao(): PreferencesDao
}
