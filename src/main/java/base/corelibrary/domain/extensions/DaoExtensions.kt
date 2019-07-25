package base.corelibrary.domain.extensions

import com.github.icarohs7.unoxandroidarch.data.db.BaseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.flow.asFlow

fun <T> BaseDao<T>.flow(): Flow<List<T>> {
    return flowable().asFlow()
}