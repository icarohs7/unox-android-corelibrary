package base.corelibrary.domain.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.icarohs7.unoxcore.extensions.coroutines.onForeground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun SwipeRefreshLayout.onRefresh(callback: () -> Unit) {
    setOnRefreshListener {
        callback()
        isRefreshing = false
    }
}

fun SwipeRefreshLayout.onRefresh(scope: CoroutineScope, callback: suspend CoroutineScope.() -> Unit) {
    setOnRefreshListener {
        scope.launch {
            callback()
            onForeground { isRefreshing = false }
        }
    }
}