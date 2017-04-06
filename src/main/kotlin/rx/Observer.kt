@file:JsModule("Rx")
@file:JsNonModule

package rx

external interface Observer<in T> {

    val closed: Boolean?

    fun next(value: T): Unit
    fun error(err: Any): Unit
    fun complete(): Unit
}