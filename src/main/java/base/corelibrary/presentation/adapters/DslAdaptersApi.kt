package base.corelibrary.presentation.adapters

import android.widget.FrameLayout
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.R
import base.corelibrary.databinding.ItemEmptyBinding

/**
 * Builder used to create a multi purpose adapter
 * and use it on the given [RecyclerView]
 * using a DSL
 * Example usage:
 * ```kotlin
 * adapter = binding.recycler.useUnoxAdapter {
 *     useItemLayout(R.layout.item_product)
 *     bind { product ->
 *         invalidateItem(product)
 *     }
 * }
 * ```
 */
@MainThread
fun <T, DB : ViewDataBinding> RecyclerView.useUnoxAdapter(
        builderBlock: UnoxAdapterBuilder<T, DB>.() -> Unit
): BaseBindingAdapter<T, DB> {
    val builder = UnoxAdapterBuilder<T, DB>(context).apply(builderBlock)
    val adapter = object : BaseBindingAdapter<T, DB>(builder.itemLayout) {
        override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
            builder.bindFun(inflateBinding(holder), position, getItem(position))
        }
    }

    this.layoutManager = builder.layoutManager
    this.adapter = adapter
    adapter.submitList(builder.items)
    return adapter
}

/**
 * Use a FrameLayout as a item layout on the
 * adapter for the given recyclerview
 */
@MainThread
fun <T> RecyclerView.useContainerAdapter(
        builder: UnoxAdapterBuilder<T, ItemEmptyBinding>.() -> Unit
): BaseBindingAdapter<T, ItemEmptyBinding> {
    return useUnoxAdapter {
        useItemLayout(R.layout.item_empty)
        builder(this)
    }
}

/**
 * Use a FrameLayout as a item layout on the
 * adapter for the given recyclerview with a
 * simplified builder
 */
@MainThread
fun <T> RecyclerView.renderContainerAdapter(
        items: List<T> = emptyList(),
        bindFun: FrameLayout.(item: T) -> Unit
): BaseBindingAdapter<T, ItemEmptyBinding> {
    return useContainerAdapter {
        bind { item -> bindFun(rootContainer, item) }
        if (items.isNotEmpty()) loadList(items)
    }
}