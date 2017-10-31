@file:JsModule("rxjs")

package rxjs

external interface Operator<T, R> {
    fun call(subscriber: Subscriber<R>, source: Any): dynamic
}