package base.corelibrary.presentation.adapters

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.icarohs7.unoxandroidarch.state.Reducer

/**
 * Builder used to create a multi purpose adapter
 * and use it on the given [RecyclerView]
 * using a DSL
 */
@MainThread
fun <T, DB : ViewDataBinding> RecyclerView.useUnoxAdapter(
        builderBlock: UnoxAdapterBuilder<T, DB>.() -> Unit
): BaseBindingAdapter<T, DB> {
    val builder = UnoxAdapterBuilder<T, DB>(context)
    builderBlock(builder)

    return buildAdapterAndSetupRecycler(this@useUnoxAdapter, builder)
}


/**
 * Function responsible of creating the adapter,
 * applying the builder and returning it
 */
@MainThread
private fun <T, DB : ViewDataBinding> buildAdapterAndSetupRecycler(
        recyclerView: RecyclerView,
        builder: UnoxAdapterBuilder<T, DB>
): BaseBindingAdapter<T, DB> {

    val adapter = object : BaseBindingAdapter<T, DB>(builder.itemLayout, builder.diffCallback) {
        override fun onBindItemToView(index: Int, item: T, view: DB) {
            builder.bindFun(view, index, item)
        }
    }

    val configuredAdapter = builder.adapterSetup(adapter)
    recyclerView.layoutManager = builder.layoutManager
    recyclerView.adapter = configuredAdapter

    adapter.submitList(builder.items)
    return configuredAdapter
}

/**
 * Builder used to make a [BaseBindingAdapter]
 * through a DSL
 */
class UnoxAdapterBuilder<T, DB : ViewDataBinding>(val context: Context) {
    internal var bindFun: DB.(index: Int, item: T) -> Unit = { _, _ -> }
    internal var itemLayout: Int = 0
    internal var diffCallback: DiffUtil.ItemCallback<T>? = null
    internal var adapterSetup: Reducer<BaseBindingAdapter<T, DB>> = { this }
    internal var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    internal var items: List<T> = emptyList()

    /**
     * Define the layout used by each
     * item of the adapter
     */
    fun useItemLayout(@LayoutRes itemLayout: Int) {
        this.itemLayout = itemLayout
    }

    /**
     * Define the function used to map an
     * item to a view
     */
    fun bind(bindFun: DB.(item: T) -> Unit) {
        this.bindFun = { _, item -> bindFun(item) }
    }

    /**
     * Define the function used to map an
     * item to a view using the item index
     * in the adapter
     */
    fun bindIndexed(bindFun: DB.(index: Int, item: T) -> Unit) {
        this.bindFun = bindFun
    }

    /**
     * Use the given list as the static
     * data source of the adapter
     */
    fun loadList(items: List<T>) {
        this.items = items
    }

    /**
     * Use the given [DiffUtil.ItemCallback] to
     * calculate the differences between 2 lists,
     * default is [BaseBindingAdapter.AllRefreshDiffCallback]
     */
    fun useDiffCallback(diffCallback: DiffUtil.ItemCallback<T>) {
        this.diffCallback = diffCallback
    }

    /**
     * Used to modify the default adapter or
     * to use another adapter
     */
    fun configAdapter(adapterSetup: Reducer<BaseBindingAdapter<T, DB>>) {
        this.adapterSetup = adapterSetup
    }

    /**
     * Modify the layout manager used by the
     * recycler view, default is [LinearLayoutManager]
     */
    fun useLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        this.layoutManager = layoutManager
    }
}