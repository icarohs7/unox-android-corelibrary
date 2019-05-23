package base.corelibrary.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import arrow.core.Try

/**
 * Base adapter based on data binding
 */
abstract class BaseBindingAdapter<T, DB : ViewDataBinding>(
        @LayoutRes val itemLayout: Int,
        diffCallback: DiffUtil.ItemCallback<T>? = null
) : ListAdapter<T, BaseBindingAdapter.BaseBindingViewHolder<DB>>(
        diffCallback ?: AllRefreshDiffCallback()
) {
    /**
     * Function converting an list item to an actual view
     */
    abstract fun onBindItemToView(index: Int, item: T, view: DB)

    /**
     * Creation of the viewholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<DB> {
        val binding: DB = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayout,
                parent,
                false
        )

        return BaseBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<DB>, position: Int) {
        Try { onBindItemToView(position, getItem(position), holder.binding) }
    }

    /**
     * Viewholder for the adapter
     */
    class BaseBindingViewHolder<DB : ViewDataBinding>(val binding: DB) : RecyclerView.ViewHolder(binding.root)

    /**
     * Default callback for lazy people, will just
     * use equals on the objects compared
     */
    class AllRefreshDiffCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}