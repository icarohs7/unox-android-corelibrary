package base.corelibrary.presentation.activities

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import base.corelibrary.presentation._baseclasses.BaseBindingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Base activity derivated from [BaseBindingActivity]
 * with an embedded timeout and a hook called when it's
 * finished
 */
abstract class BaseTimeoutActivity<DB : ViewDataBinding>(
        val timeout: Int = 2000
) : BaseBindingActivity<DB>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            beforeTimeout()
            delay(timeout.toLong())
            onTimeout()
        }
    }

    /** Called before the timer is started */
    open suspend fun beforeTimeout() {
    }

    /** Called after the timeout is finished */
    abstract fun onTimeout()
}