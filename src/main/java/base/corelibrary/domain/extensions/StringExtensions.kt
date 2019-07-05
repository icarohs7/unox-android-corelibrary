package base.corelibrary.domain.extensions

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse

fun <T : Any> String.deserialize(deserializer: KSerializer<T>): T {
    return Json.parse(deserializer, this)
}

@ImplicitReflectionSerializer
inline fun <reified T : Any> String.deserialize(): T {
    return Json.parse(this)
}