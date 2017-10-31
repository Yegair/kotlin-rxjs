@file:JsModule("rxjs")

package rxjs

external interface Observer<in T> {

    val closed: Boolean?

    fun next(value: T)
    fun error(err: Any)
    fun complete()
}