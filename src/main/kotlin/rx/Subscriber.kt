@file:JsModule("Rx")
@file:JsNonModule

package rx

external class Subscriber<T>(destination: Observer<T>,
                             error: (e: Any?) -> Unit,
                             complete: () -> Unit
) : Subscription, Observer<T> {

    constructor(next: (x: T?) -> Unit,
                error: (e: Any?) -> Unit,
                complete: () -> Unit)

    override fun next(value: T)
    override fun error(err: Any)
    override fun complete()

    override fun unsubscribe()

    companion object {

        fun <T> create(): Subscriber<T>

        fun <T> create(next: (x: T?) -> Unit): Subscriber<T>

        fun <T> create(next: (x: T?) -> Unit,
                       error: (e: Any?) -> Unit): Subscriber<T>

        fun <T> create(next: (x: T?) -> Unit,
                       error: (e: Any?) -> Unit,
                       complete: () -> Unit): Subscriber<T>
    }
}
