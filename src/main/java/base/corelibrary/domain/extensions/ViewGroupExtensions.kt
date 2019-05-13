package base.corelibrary.domain.extensions

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

/**
 * Add the root of the given data binding
 * layout to the receiver view group
 */
operator fun ViewGroup.plusAssign(view: ViewDataBinding) {
    this.addView(view.root)
}