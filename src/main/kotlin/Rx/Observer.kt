@file:JsModule(ModuleName)
@file:JsNonModule

package Rx

external interface Observer<in T> {

    val closed: Boolean?

    fun next(value: T): Unit
    fun error(err: Any): Unit
    fun complete(): Unit
}