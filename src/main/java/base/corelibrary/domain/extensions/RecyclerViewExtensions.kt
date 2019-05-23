package base.corelibrary.domain.extensions

import android.widget.FrameLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.R
import base.corelibrary.databinding.ItemEmptyBinding
import base.corelibrary.domain.callbacks.SimpleSwipeCallback
import base.corelibrary.presentation.adapters.BaseBindingAdapter
import base.corelibrary.presentation.adapters.UnoxAdapterBuilder
import base.corelibrary.presentation.adapters.useUnoxAdapter

fun <T> RecyclerView.useContainerAdapter(
        builder: UnoxAdapterBuilder<T, ItemEmptyBinding>.() -> Unit
): BaseBindingAdapter<T, ItemEmptyBinding> {
    return useUnoxAdapter {
        useItemLayout(R.layout.item_empty)
        builder(this)
    }
}

fun <T> RecyclerView.renderContainerAdapter(
        items: List<T> = emptyList(),
        bindFun: FrameLayout.(item: T) -> Unit
): BaseBindingAdapter<T, ItemEmptyBinding> {
    return useContainerAdapter {
        bind { item -> bindFun(rootContainer, item) }
        if (items.isNotEmpty()) loadList(items)
    }
}

fun RecyclerView.onItemSwipe(onSwiped: (viewHolder: RecyclerView.ViewHolder, direction: Int) -> Unit) {
    val callback = object : SimpleSwipeCallback() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            onSwiped(viewHolder, direction)
        }
    }
    ItemTouchHelper(callback).attachToRecyclerView(this)
}