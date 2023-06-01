package agency.five.codebase.android.newsapp.data.network.mapper

class UrlParamsMapperImpl : UrlParamsMapper {
    override fun toUrlCategories(categories: List<String>): List<String> {
        return categories.map {
            it.lowercase()
        }
    }

    override fun toUrlCountries(countries: List<String>): List<String> {
        return countries.map {
            when (it) {
                "Argentina" -> "ar"
                "Australia" -> "au"
                "Austria" -> "at"
                "Belgium" -> "be"
                "Brazil" -> "br"
                "Bulgaria" -> "bg"
                "Canada" -> "ca"
                "China" -> "cn"
                "Colombia" -> "co"
                "Czech Republic" -> "cz"
                "Egypt" -> "eg"
                "France" -> "fr"
                "Germany" -> "de"
                "Greece" -> "gr"
                "Hong Kong" -> "hk"
                "Hungary" -> "hu"
                "India" -> "in"
                "Indonesia" -> "id"
                "Ireland" -> "ie"
                "Israel" -> "il"
                "Italy" -> "it"
                "Japan" -> "jp"
                "Latvia" -> "lv"
                "Lithuania" -> "lt"
                "Malaysia" -> "my"
                "Mexico" -> "mx"
                "Morocco" -> "ma"
                "Netherlands" -> "nl"
                "New Zealand" -> "nz"
                "Nigeria" -> "ng"
                "Norway" -> "no"
                "Philippines" -> "ph"
                "Poland" -> "pl"
                "Portugal" -> "pt"
                "Romania" -> "ro"
                "Saudi Arabia" -> "sa"
                "Serbia" -> "rs"
                "Singapore" -> "sg"
                "Slovakia" -> "sk"
                "Slovenia" -> "si"
                "South Africa" -> "za"
                "South Korea" -> "kr"
                "Sweden" -> "se"
                "Switzerland" -> "ch"
                "Taiwan" -> "tw"
                "Thailand" -> "th"
                "Turkey" -> "tr"
                "UAE" -> "ae"
                "Ukraine" -> "ua"
                "United Kingdom" -> "gb"
                "United States" -> "us"
                else -> "ve"
            }
        }
    }

    override fun toUrlLanguages(languages: List<String>): List<String> {
        return languages.map {
            when (it) {
                "Arabic" -> "ar"
                "Chinese" -> "zh"
                "Dutch" -> "nl"
                "English" -> "en"
                "French" -> "fr"
                "German" -> "de"
                "Italian" -> "it"
                "Norwegian" -> "no"
                "Portuguese" -> "pt"
                "Russian" -> "ru"
                "Spanish" -> "es"
                else -> "sv"
            }
        }
    }
}
