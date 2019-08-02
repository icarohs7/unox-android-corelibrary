package base.corelibrary.presentation.activities

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.core.view.isInvisible
import com.github.icarohs7.unoxandroidarch.R
import com.github.icarohs7.unoxandroidarch.databinding.ActivityBaseNxSplashBinding
import com.github.icarohs7.unoxandroidarch.extensions.animateFadeIn
import com.github.icarohs7.unoxandroidarch.state.addOnLoadingListener

abstract class BaseSplashNxActivity(
        private val version: String,
        timeout: Int = 0,
        checkAppUpdate: Boolean = true
) : BaseTimeoutActivity<ActivityBaseNxSplashBinding>(timeout, checkAppUpdate) {

    @CallSuper
    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        addOnLoadingListener { toggleLoading(it) }
        binding.txtVersion.text = version
        binding.imgLogo.alpha = 0f
        binding.imgLogo.animateFadeIn((timeout).toLong())
    }

    private fun toggleLoading(isLoading: Boolean) {
        binding.progressBar.isInvisible = !isLoading
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_nx_splash
    }
}