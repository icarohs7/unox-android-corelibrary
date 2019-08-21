package base.corelibrary.domain.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

inline fun SwipeRefreshLayout.refreshWhileRunning(block: () -> Unit) {
    try {
        isRefreshing = true
        block()
    } finally {
        isRefreshing = false
    }
}