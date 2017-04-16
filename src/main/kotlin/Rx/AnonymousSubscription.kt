@file:JsModule(ModuleName)
@file:JsNonModule

package Rx

external interface AnonymousSubscription {
    fun unsubscribe(): Unit
}