package base.corelibrary.presentation._baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.airbnb.mvrx.BaseMvRxFragment
import com.github.icarohs7.unoxcore.extensions.coroutines.job
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Base fragment using databinding
 */
abstract class BaseBindingFragment<B : ViewDataBinding> : BaseMvRxFragment() {
    /**
     * Initialized on [onCreateView]
     */
    lateinit var binding: B
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
                .inflate<B>(inflater, getLayout(), container, false)
                .apply { lifecycleOwner = this@BaseBindingFragment }

        afterCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    /**
     * Called just after the [binding] is defined,
     * inside the onCreateView event
     */
    open fun afterCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
    }

    override fun invalidate() {
    }

    override fun onStart() {
        super.onStart()
        onBindingCreated()
    }

    /**
     * Called after the binding is created
     */
    open fun onBindingCreated() {
    }

    /**
     * Launch the collection of the given Flow
     * on the coroutine scope of this component
     */
    fun Flow<*>.launchInScope(): Job = launchIn(lifecycleScope)

    /**
     * Launch the collection of the given Flow,
     * executing the given action on each emission,
     * using the scope of the current component to
     * on the coroutine created
     */
    fun <T> Flow<T>.launchCollect(action: suspend (T) -> Unit): Job {
        return onEach(action).launchInScope()
    }

    /**
     * Cancel all children coroutines in the
     * onStop event
     */
    override fun onStop() {
        super.onStop()
        lifecycleScope.job.cancelChildren()
    }

    /**
     * @return layout to setup data binding.
     */
    @LayoutRes
    abstract fun getLayout(): Int
}