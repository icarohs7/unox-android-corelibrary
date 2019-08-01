package base.corelibrary.domain.extensions

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class RxJavaExtensionsKtTest {
    @Test
    fun `should convert observable to flow`(): Unit = runBlockingTest {
        //Assert emission
        val obs1 = Observable.fromArray(1, 2, 3)
        val items1 = obs1.asFlow().toList()
        items1 shouldEqual listOf(1, 2, 3)

        //Assert onError
        val obs2 = Observable.create<Int> { emitter ->
            emitter.onNext(10)
            emitter.onNext(20)
            emitter.onError(IllegalArgumentException("NANI!?"))
            emitter.onNext(30)
        }
        val items2 = obs2
                .asFlow()
                .catch { ex ->
                    ex.localizedMessage shouldEqual "NANI!?"
                    emit(1532)
                }
                .toList()

        items2 shouldEqual listOf(10, 20, 1532)
    }

    @Test
    fun `should convert flowable to flow`(): Unit = runBlockingTest {
        //Assert emission
        val flowable1 = Flowable.fromArray(1, 2, 3)
        val items1 = flowable1.asFlow().toList()
        items1 shouldEqual listOf(1, 2, 3)

        //Assert onError
        val flowable2 = Flowable.create<Int>({ emitter ->
            emitter.onNext(10)
            emitter.onNext(20)
            emitter.onError(IllegalArgumentException("NANI!?"))
            emitter.onNext(30)
        }, BackpressureStrategy.LATEST)
        val items2 = flowable2
                .asFlow()
                .catch { ex ->
                    ex.localizedMessage shouldEqual "NANI!?"
                    emit(1532)
                }
                .toList()

        items2 shouldEqual listOf(10, 20, 1532)
    }
}