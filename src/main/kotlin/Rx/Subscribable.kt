@file:JsModule(ModuleName)
@file:JsNonModule

package Rx

external interface Subscribable<out T> {

    fun subscribe(): Subscription

    fun subscribe(next: (value: T) -> Unit): Subscription

    fun subscribe(next: (value: T) -> Unit,
                  error: (err: Any?) -> Unit): Subscription

    fun subscribe(next: (value: T) -> Unit,
                  error: (err: Any?) -> Unit,
                  complete: () -> Unit): Subscription
}