package base.corelibrary.domain.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import arrow.core.Try
import com.github.icarohs7.unoxcore.tryBg
import java.net.URL

/**
 * Make an HTTP request to the given URL
 * and return the body of the response
 */
suspend fun URL.httpGetBody(): Try<String> {
    return tryBg {
        openStream()
                .bufferedReader()
                .useLines { lines -> lines.joinToString("") }
    }
}

/**
 * Get the image from the given url and
 * convert it to a bitmap
 */
suspend fun URL.toBitmap(): Try<Bitmap> {
    return tryBg { openStream().use(BitmapFactory::decodeStream) }
}

/**
 * Decode the response body of the requisition
 * on the given URL and return the bitmap read
 * from the base64 string
 */
suspend fun URL.base64ToBitmap(): Try<Bitmap> {
    return tryBg { (!!httpGetBody()).base64ToBitmap() }
}