package base.corelibrary.domain.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Navigate to the giving location popping out all backstack
 * destinations in between
 */
fun NavController.navigatePopping(@IdRes destination: Int, args: Bundle? = null) {
    val navOptions = NavOptions
            .Builder()
            .setPopUpTo(destination, true)
            .build()
    this.navigate(destination, args, navOptions)
}