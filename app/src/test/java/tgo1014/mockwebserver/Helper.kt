package tgo1014.mockwebserver

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

object Helper {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Create an instance of the Retrofit class
     */
    internal fun generateRetrofit(mockWebServer: MockWebServer): Retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/")) // Dummy url for testing
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * Sets which response the [MockWebServer] should return when a request is made
     */
    fun MockWebServer.setResponse(fileName: String, responseCode: Int = 200) {
        enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getFileAsString(fileName))
        )
    }

    /**
     * The the file in the [filePath] and return its content as a [String]
     */
    private fun getFileAsString(filePath: String): String {
        val uri = ClassLoader.getSystemResource(filePath)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}