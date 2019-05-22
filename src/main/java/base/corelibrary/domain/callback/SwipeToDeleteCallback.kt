package base.corelibrary.domain.callback

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import base.corelibrary.R
import splitties.resources.appColor
import splitties.resources.appDrawable

abstract class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {

    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val width = (itemView.bottom - itemView.top) / 3

        val (background, iconDest) = when (dX > 0) {
            true -> {
                RectF(
                        itemView.left.toFloat(),
                        itemView.top.toFloat(),
                        dX,
                        itemView.bottom.toFloat()
                ) to RectF(
                        itemView.left.toFloat() + width,
                        itemView.top.toFloat() + width,
                        itemView.left.toFloat() + 2 * width,
                        itemView.bottom.toFloat() - width
                )
            }
            false -> {
                RectF(
                        itemView.right.toFloat() + dX,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat()
                ) to RectF(
                        itemView.right.toFloat() - 2 * width,
                        itemView.top.toFloat() + width,
                        itemView.right.toFloat() - width,
                        itemView.bottom.toFloat() - width
                )
            }
        }
        val icon = appDrawable(R.drawable.ic_delete_white_24dp)!!.toBitmap()

        val paint = Paint().apply { color = appColor(R.color.colorError) }
        canvas.drawRect(background, paint)
        canvas.drawBitmap(icon, null, iconDest, paint)

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}