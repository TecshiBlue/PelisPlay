import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Pelicula(
    val id: Int,
    val idDrawable: Int,
    val titulo: String
) {
    companion object {
        fun fromJsonList(json: String): List<Pelicula> {
            val type = object : TypeToken<List<Pelicula>>() {}.type
            return Gson().fromJson(json, type)
        }

        fun toJsonList(lista: List<Pelicula>): String {
            return Gson().toJson(lista)
        }
    }
}
