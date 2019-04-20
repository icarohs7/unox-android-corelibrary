package base.corelibrary.domain.extensions

import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.github.icarohs7.unoxandroidarch.UnoxAndroidArch

fun UnoxAndroidArch.AnimationType.toNavOptions(): NavOptions {
    return navOptions {
        anim {
            enter = enterRes
            exit = exitRes
            popEnter = enterRes
            popExit = exitRes
        }
    }
}