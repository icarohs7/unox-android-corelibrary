package base.corelibrary.domain.extensions

import base.corelibrary.testutils.TestClass
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import se.lovef.assert.v1.shouldEqual

class RetrofitExtensionsKtTest {
    interface TestApiService {
        @GET("/message")
        suspend fun getMessage(): TestClass

        @GET("/numbers")
        suspend fun getNumbers(): List<Int>
    }

    @Test
    fun `should create and execute retrofit request`() {
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody("""{"id":0,"message":"Omai wa mou shindeiru!"}"""))
        server.enqueue(MockResponse().setBody("[10,20,30,40]"))
        server.start()

        val retrofit = retrofitInstance(server.url("/").toString())

        val response1 = runBlocking { retrofit.getMessage() }
        response1 shouldEqual TestClass(message = "Omai wa mou shindeiru!")

        val response2 = runBlocking { retrofit.getNumbers() }
        response2 shouldEqual listOf(10, 20, 30, 40)
    }

    private fun retrofitInstance(baseUrl: String): TestApiService {
        return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(RetrofitExtensions.getKotlinxSerializationConverter())
                .build()
                .create()
    }
}