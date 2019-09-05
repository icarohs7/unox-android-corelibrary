package base.corelibrary.domain.extensions

import com.github.icarohs7.unoxcore.extensions.orThrow
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class UrlExtensionsTest {
    @Test
    fun `should get body of request from http url`() { //TODO cover error on request
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody("Omai wa mou shindeiru!"))
        server.start()

        val response = runBlocking { server.url("/").toUrl().httpGetBody() }.orThrow()
        response shouldEqual "Omai wa mou shindeiru!"
    }
}