package agency.five.codebase.android.newsapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun stringToList(value: String?): List<String> {
        if (value.isNullOrBlank()) {
            return listOf()
        }
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun listToString(list: List<String>): String? {
        return Gson().toJson(list)
    }
}
