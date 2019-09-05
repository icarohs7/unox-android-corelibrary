package base.corelibrary.domain.extensions

import android.os.Build
import androidx.core.os.bundleOf
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O])
class BundleExtensionsKtTest {
    @Test
    fun `should convert bundle to map`() {
        val bundle = bundleOf("name" to "Icaro", "age" to 22)
        println(bundle)
        bundle.toMap() shouldEqual mapOf("name" to "Icaro", "age" to 22)
    }
}