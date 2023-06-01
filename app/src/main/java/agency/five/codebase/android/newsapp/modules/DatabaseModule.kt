package agency.five.codebase.android.newsapp.modules

import agency.five.codebase.android.newsapp.data.database.PreferencesDatabase
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val dbName = "preferences.db"
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            PreferencesDatabase::class.java,
            dbName
        ).build()
    }
    single {
        get<PreferencesDatabase>().getDao()
    }
}
