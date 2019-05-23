package base.corelibrary.testutils

import kotlinx.serialization.Serializable

@Serializable
data class TestClass(val id: Int = 0, val message: String)