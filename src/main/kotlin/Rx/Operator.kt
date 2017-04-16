@file:JsModule(ModuleName)
@file:JsNonModule

package Rx

external interface Operator<T, R> {
    fun call(subscriber: Subscriber<R>, source: Any): dynamic
}