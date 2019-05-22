package base.corelibrary.domain.callbacks

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleSwipeCallback(
        swipeDirs: Int = ItemTouchHelper.START or ItemTouchHelper.END
) : ItemTouchHelper.SimpleCallback(0, swipeDirs) {
    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}