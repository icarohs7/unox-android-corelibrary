package base.corelibrary.domain.extensions

import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun ChipGroup.checkedChipFlow(): Flow<Int> {
    return callbackFlow {
        send(checkedChipId)
        setOnCheckedChangeListener { _, checkedId -> offer(checkedId) }
        awaitClose()
    }
}