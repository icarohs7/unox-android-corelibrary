package base.corelibrary.domain.extensions

import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.R
import base.corelibrary.databinding.ItemEmptyBinding
import com.github.icarohs7.unoxandroidarch.presentation.adapters.BaseBindingAdapter
import com.github.icarohs7.unoxandroidarch.presentation.adapters.UnoxAdapterBuilder
import com.github.icarohs7.unoxandroidarch.presentation.adapters.useUnoxAdapter

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