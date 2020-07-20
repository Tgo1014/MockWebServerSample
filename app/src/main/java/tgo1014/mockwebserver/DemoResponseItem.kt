package tgo1014.mockwebserver


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DemoResponseItem(
    @Json(name = "body")
    val body: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "userId")
    val userId: Int? = null
)