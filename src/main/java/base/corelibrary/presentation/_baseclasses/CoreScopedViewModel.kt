package base.corelibrary.presentation._baseclasses

import com.github.icarohs7.unoxandroidarch.presentation.viewmodel.BaseScopedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

abstract class CoreScopedViewModel : BaseScopedViewModel() {
    /**
     * Launch the collection of the given Flow
     * on the coroutine scope of this component
     */
    fun Flow<*>.launchInScope(): Job = launchIn(this@CoreScopedViewModel)
}