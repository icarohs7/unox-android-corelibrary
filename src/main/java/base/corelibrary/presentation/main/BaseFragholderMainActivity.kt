package base.corelibrary.presentation.main

import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import base.corelibrary.R
import com.github.icarohs7.unoxandroidarch.presentation.activities.BaseArchActivity
import com.github.icarohs7.unoxandroidarch.state.LoadingStore.toggleLoading
import com.github.icarohs7.unoxandroidarch.state.addOnLoadingListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

abstract class BaseFragholderMainActivity : BaseArchActivity() {
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progress_bar) }
    val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragholder_main)
        lifecycleScope.addOnLoadingListener { isLoading -> progressBar.isVisible = isLoading }

    }

    /**
     * Launch the collection of the given Flow
     * on the coroutine scope of this component
     */
    fun Flow<*>.launchInScope(): Job = launchIn(lifecycleScope)
}