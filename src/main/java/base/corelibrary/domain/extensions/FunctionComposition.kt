/**
 * Extensions used to compose functions,
 * taken from [arrow-kt](https://github.com/arrow-kt/arrow/blob/12bfc77fe44844ebff87ce7a244f418a34d063a1/modules/core/arrow-syntax/src/main/kotlin/arrow/syntax/function/composition.kt)
 */
package base.corelibrary.domain.extensions

inline infix fun <P1, P2, IP, R> ((P1, P2) -> IP).andThen(crossinline f: (IP) -> R): (P1, P2) -> R = forwardCompose(f)

inline infix fun <P1, IP, R> ((P1) -> IP).andThen(crossinline f: (IP) -> R): (P1) -> R = forwardCompose(f)

inline infix fun <IP, R> (() -> IP).andThen(crossinline f: (IP) -> R): () -> R = forwardCompose(f)

inline infix fun <P1, P2, IP, R> ((P1, P2) -> IP).forwardCompose(crossinline f: (IP) -> R) = { p1: P1, p2: P2 ->
    f(this(p1, p2))
}

inline infix fun <P1, IP, R> ((P1) -> IP).forwardCompose(crossinline f: (IP) -> R): (P1) -> R = { p1: P1 ->
    f(this(p1))
}

inline infix fun <IP, R> (() -> IP).forwardCompose(crossinline f: (IP) -> R): () -> R = { f(this()) }

inline infix fun <IP, R, P1> ((IP) -> R).compose(crossinline f: (P1) -> IP): (P1) -> R = { p1: P1 -> this(f(p1)) }