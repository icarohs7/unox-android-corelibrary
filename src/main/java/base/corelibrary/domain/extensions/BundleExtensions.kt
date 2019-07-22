package base.corelibrary.domain.extensions

import android.os.Bundle

/**
 * Create a map with the data of the
 * given Bundle
 */
fun Bundle.toMap(): Map<String, Any?> = keySet().associateWith(this::get)