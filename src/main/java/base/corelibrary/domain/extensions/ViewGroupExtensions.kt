package base.corelibrary.domain.extensions

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

/**
 * Remove all views and add the given
 * view to the viewgroup
 */
fun ViewGroup.replaceAllViews(view: View) {
    removeAllViews()
    addView(view)
}

/**
 * Remove all views and add the given
 * databinding view root to the viewgroup
 */
fun ViewGroup.replaceAllViews(databinding: ViewDataBinding) {
    removeAllViews()
    addView(databinding.root)
}