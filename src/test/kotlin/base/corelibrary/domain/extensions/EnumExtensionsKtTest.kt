package base.corelibrary.domain.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class EnumExtensionsKtTest {
    enum class TestEnum { A, B, C; }

    @Test
    fun `should convert enum to int`() {
        TestEnum.A.toInt() shouldEqual 0
        TestEnum.B.toInt() shouldEqual 1
        TestEnum.C.toInt() shouldEqual 2
    }

    @Test
    fun `should convert int to enum`() {
        0.toEnum<TestEnum>() shouldEqual TestEnum.A
        1.toEnum<TestEnum>() shouldEqual TestEnum.B
        2.toEnum<TestEnum>() shouldEqual TestEnum.C
    }
}