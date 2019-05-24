package base.corelibrary.presentation.adapters

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Builder used to make a [BaseBindingAdapter]
 * through a DSL
 */
class UnoxAdapterBuilder<T, DB : ViewDataBinding>(val context: Context) {
    internal var bindFun: DB.(index: Int, item: T) -> Unit = { _, _ -> }
    internal var itemLayout: Int = 0
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
     * Modify the layout manager used by the
     * recycler view, default is [LinearLayoutManager]
     */
    fun useLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        this.layoutManager = layoutManager
    }
}