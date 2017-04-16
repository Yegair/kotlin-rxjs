@file:JsModule(ModuleName)
@file:JsNonModule

package Rx

external open class Subject<T> : Observable<T>, Observer<T> {

    // --- Observer

    override val closed: Boolean?

    override fun next(value: T)
    override fun error(err: Any)
    override fun complete()


    // --- Observable

    override fun <R> lift(operator: Operator<T, R>): Subject<R>

}