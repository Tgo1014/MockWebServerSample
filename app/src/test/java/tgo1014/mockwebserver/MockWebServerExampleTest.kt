package tgo1014.mockwebserver

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import tgo1014.mockwebserver.Helper.generateRetrofit
import tgo1014.mockwebserver.Helper.setResponse
import java.lang.Exception

class MockWebServerExampleTest {

    private val mockWebServer = MockWebServer()
    private val api: Api by lazy { generateRetrofit(mockWebServer).create(Api::class.java) }

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @Test
    fun `Get Post List Should Not Be Empty`() {
        // Given A Post List Request
        val interactor = GetPostsInteractor(api)
        // When Executing The Request
        mockWebServer.setResponse("response.json")
        val postList = interactor.execute()
        // Then Posts Should Not Be Empty
        assert(postList.isNotEmpty())
    }

    @Test
    fun `Get Post List Should Throw Error`() {
        // Given A Post List Request
        val interactor = GetPostsInteractor(api)
        // When Executing The Request
        mockWebServer.setResponse("response.json", 404)
        try {
            val postList = interactor.execute()
            assert(false)
        } catch (e: Exception) {
            // Then An Error Should Be Thrown
            assert(true)
        }
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

}