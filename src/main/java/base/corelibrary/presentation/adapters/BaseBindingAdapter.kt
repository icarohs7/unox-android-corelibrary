package base.corelibrary.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * Base adapter based on data binding
 */
abstract class BaseBindingAdapter<T, DB : ViewDataBinding>(
        @LayoutRes val itemLayout: Int,
        diffCallback: DiffUtil.ItemCallback<T>? = null
) : ListAdapter<T, SimpleViewHolder>(
        diffCallback ?: AllRefreshDiffCallback()
) {
    /**
     * Creation of the viewholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val binding: DB = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayout,
                parent,
                false
        )

        return SimpleViewHolder(binding.root)
    }

    /**
     * Retrieve the databinding class of the given viewholder
     */
    fun inflateBinding(viewHolder: SimpleViewHolder): DB {
        return requireNotNull(DataBindingUtil.findBinding(viewHolder.view))
    }

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