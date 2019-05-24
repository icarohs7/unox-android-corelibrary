package base.corelibrary.presentation.adapters

import android.widget.FrameLayout
import androidx.annotation.MainThread
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.R
import base.corelibrary.databinding.ItemEmptyBinding
import base.corelibrary.databinding.LayoutSwipeMenuDeleteBinding
import base.corelibrary.databinding.LayoutSwipeMenuEditDeleteBinding
import base.corelibrary.databinding.LayoutSwipeMenuRightBinding
import base.corelibrary.domain.extensions.plusAssign
import kotlinx.coroutines.launch
import splitties.dimensions.dip
import splitties.systemservices.layoutInflater

/**
 * Builder used to create a multi purpose adapter
 * and use it on the given [RecyclerView]
 * using a DSL
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

@MainThread
fun <T> RecyclerView.useRightSwipeContainerAdapter(
        builder: UnoxAdapterBuilder<T, LayoutSwipeMenuRightBinding>.() -> Unit
): BaseBindingAdapter<T, LayoutSwipeMenuRightBinding> {
    return useUnoxAdapter {
        useItemLayout(R.layout.layout_swipe_menu_right)
        builder(this)
    }
}

fun <T> RecyclerView.renderRightSwipeContainerAdapter(
        items: List<T> = emptyList(),
        bindFun: LayoutSwipeMenuRightBinding.(item: T) -> Unit
): BaseBindingAdapter<T, LayoutSwipeMenuRightBinding> {
    return useRightSwipeContainerAdapter {
        bind { item -> bindFun(item) }
        if (items.isNotEmpty()) loadList(items)
    }
}

fun <T> RecyclerView.renderSwipeDeleteMenu(
        items: List<T> = emptyList(),
        bindFun: (item: T, layoutContent: FrameLayout, swipeMenu: LayoutSwipeMenuDeleteBinding) -> Unit
): BaseBindingAdapter<T, LayoutSwipeMenuRightBinding> {
    return useRightSwipeContainerAdapter {
        bind { item ->
            val swipeMenu = LayoutSwipeMenuDeleteBinding.inflate(layoutInflater).apply {
                layoutUnderContent += this
            }
            bindFun(item, layoutOverContent, swipeMenu)
        }
        if (items.isNotEmpty()) loadList(items)
    }
}

fun <T> RecyclerView.renderSwipeEditDeleteMenu(
        items: List<T> = emptyList(),
        bindFun: (item: T, layoutContent: FrameLayout, swipeMenu: LayoutSwipeMenuEditDeleteBinding) -> Unit
): BaseBindingAdapter<T, LayoutSwipeMenuRightBinding> {
    return useRightSwipeContainerAdapter {
        bind { item ->
            val swipeMenu = LayoutSwipeMenuEditDeleteBinding.inflate(layoutInflater).apply {
                layoutUnderContent += this
            }
            bindFun(item, layoutOverContent, swipeMenu)
        }
        if (items.isNotEmpty()) loadList(items)
    }
}