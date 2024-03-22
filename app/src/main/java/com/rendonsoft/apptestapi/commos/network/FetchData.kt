import com.google.gson.Gson
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

suspend fun fetchData(urlString: String): List<AutoItem> {
    return withContext(Dispatchers.IO) {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val jsonString = connection.inputStream.bufferedReader().use { it.readText() }
            val gson = Gson()
            gson.fromJson(jsonString, Array<AutoItem>::class.java).toList()
        } else {
            emptyList()
        }
    }
}
