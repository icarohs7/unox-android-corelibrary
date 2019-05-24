package base.corelibrary.domain.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.domain.callbacks.SimpleSwipeCallback

fun RecyclerView.onItemSwipe(onSwiped: (viewHolder: RecyclerView.ViewHolder, direction: Int) -> Unit) {
    val callback = object : SimpleSwipeCallback() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            onSwiped(viewHolder, direction)
        }
    }
    ItemTouchHelper(callback).attachToRecyclerView(this)
}