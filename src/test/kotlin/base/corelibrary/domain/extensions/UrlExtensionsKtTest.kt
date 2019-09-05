package base.corelibrary.domain.extensions

import arrow.core.Failure
import com.github.icarohs7.unoxcore.extensions.orThrow
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.typeIs
import java.net.URL

class UrlExtensionsKtTest {
    @Test
    fun `should get body of request from http url`() { //TODO cover error on request
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody("Omai wa mou shindeiru!"))
        server.start()

        val response1 = runBlocking { server.url("/").toUrl().httpGetBody() }.orThrow()
        response1 shouldEqual "Omai wa mou shindeiru!"

        val response2 = runBlocking { URL("https://shouldnotwork.should.not.work").httpGetBody() }
        response2 typeIs Failure::class
    }
}