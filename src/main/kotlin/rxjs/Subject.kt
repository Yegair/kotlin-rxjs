@file:JsModule("rxjs")

package rxjs

external open class Subject<T> : Observable<T>, ISubscription, Observer<T> {

    /**
     * The observers that have subscribed to this Subject.
     */
    val observers: Array<Observer<T>>

    /**
     * @see Observer.closed
     * @see ISubscription.closed
     */
    override val closed: Boolean?

    val isStopped: Boolean

    val hasError: Boolean

    val thrownError: Any

    /**
     * @see Observable.lift
     */
    override fun <R> lift(operator: Operator<T, R>): Subject<R>

    /**
     * @see Observer.next
     */
    override fun next(value: T)

    /**
     * @see Observer.error
     */
    override fun error(err: Any)

    /**
     * @see Observer.complete
     */
    override fun complete()

    /**
     * @see AnonymousSubscription.unsubscribe
     */
    override fun unsubscribe()

    protected fun _trySubscribe(subscriber: Subscriber<T>): Any

    protected fun _subscribe(subscriber: Subscriber<T>): Subscription

    /**
     * Converts this Subject to a [Observable]
     */
    fun asObservable(): Observable<T>

    companion object {

        fun <T> create(): Subject<T>
    }
}