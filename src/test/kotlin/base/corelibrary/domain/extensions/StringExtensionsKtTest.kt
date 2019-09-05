package base.corelibrary.domain.extensions

import base.corelibrary.testutils.TestClass
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class StringExtensionsKtTest {
    @Test
    fun `should deserialize string using explicit deserializer`() {
        val item = """
            {
                "id": 1532,
                "message": "ZA WARUDO!"
            }
        """.trimIndent()
        val deserializer = TestClass.serializer()
        item.deserialize<TestClass>(deserializer) shouldEqual TestClass(1532, "ZA WARUDO!")
    }

    @UseExperimental(ImplicitReflectionSerializer::class)
    @Test
    fun `should deserialize string using implicit deserializer`() {
        val item = """
            {
                "id": 1532,
                "message": "ZA WARUDO!"
            }
        """.trimIndent()
        item.deserialize<TestClass>() shouldEqual TestClass(1532, "ZA WARUDO!")
    }
}