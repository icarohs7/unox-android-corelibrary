package base.corelibrary.presentation.custom

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class HardSwipeRefresh @JvmOverloads constructor(
        ctx: Context,
        attrs: AttributeSet? = null
) : SwipeRefreshLayout(ctx, attrs) {
    init {
        setDistanceToTriggerSync(350)
    }
}