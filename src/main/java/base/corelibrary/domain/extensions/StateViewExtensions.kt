package base.corelibrary.domain.extensions

import com.umutbey.stateviews.StateView

fun StateView.displayStateWhenListIsEmpty(list: List<*>, stateTag: String) {
    if (list.isEmpty()) displayState(stateTag)
    else hideStates()
}