@file:JsModule("Rx")
@file:JsNonModule

package rx

external interface Operator<T, R> {
    fun call(subscriber: Subscriber<R>, source: Any): dynamic
}