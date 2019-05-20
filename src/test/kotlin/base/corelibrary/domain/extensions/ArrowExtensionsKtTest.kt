package base.corelibrary.domain.extensions

import com.github.icarohs7.unoxcore.sideEffect
import org.junit.Test
import se.lovef.assert.v1.shouldBeFalse
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.throws

class ArrowExtensionsKtTest {
    @Test
    fun should_return_success_result_of_IO() {
        val i1 = sideEffect { throw Exception() }
        i1.syncIsSuccess.shouldBeFalse()

        val i2 = sideEffect { 10 }
        i2.syncIsSuccess.shouldBeTrue()

        val i3 = sideEffect { false }
        i3.syncIsSuccess.shouldBeFalse()

        val i4 = sideEffect { true }
        i4.syncIsSuccess.shouldBeTrue()
    }

    @Test
    fun should_unwrap_io_value_unsafely() {
        val i1 = sideEffect { throw Exception() }
        ;{ !!i1 } throws Exception::class

        val i2 = sideEffect { 10 }
        !!i2 shouldEqual 10

        val i3 = sideEffect { false }
        !!i3 shouldEqual false

        val i4 = sideEffect { true }
        !!i4 shouldEqual true
    }
}