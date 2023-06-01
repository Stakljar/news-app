package agency.five.codebase.android.newsapp.data.database

import agency.five.codebase.android.newsapp.model.Preference
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreferenceEnt(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "keyword")
    val keyword: String,
    @ColumnInfo(name = "categories")
    val categories: List<String>,
    @ColumnInfo(name = "countries")
    val countries: List<String>,
    @ColumnInfo(name = "languages")
    val languages: List<String>,
    @ColumnInfo(name = "is_default")
    val isDefault: Boolean
) {
    fun toPreference(): Preference {
        return Preference(
            id = id,
            keyword = keyword,
            categories = categories,
            countries = countries,
            languages = languages,
            isDefault = isDefault
        )
    }
}
