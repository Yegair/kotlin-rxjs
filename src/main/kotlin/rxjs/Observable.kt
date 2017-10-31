@file:JsModule("rxjs")

package rxjs

external open class Observable<T> : Subscribable<T> {

    override fun subscribe(): Subscription
    override fun subscribe(next: (value: T) -> Unit): Subscription
    override fun subscribe(next: (value: T) -> Unit,
                           error: (err: Any?) -> Unit): Subscription

    override fun subscribe(next: (value: T) -> Unit,
                           error: (err: Any?) -> Unit,
                           complete: () -> Unit): Subscription

    /**
     * Catches errors on the observable to be handled by returning a new observable or throwing an error.
     *
     * @param selector a function that takes as arguments `err`, which is the error. Whatever observable is returned by
     * the `selector` will be used to continue the observable chain.
     */
    fun catch(selector: (err: Any) -> Observable<T>): Observable<T>

    /**
     * Catches errors on the observable to be handled by returning a new observable or throwing an error.
     *
     * @param selector a function that takes as arguments `err`, which is the error, and `caught`, which is the source
     * observable, in case you'd like to "retry" that observable by returning it again. Whatever observable is returned
     * by the `selector` will be used to continue the observable chain.
     */
    fun catch(selector: (err: Any, caught: Observable<T>) -> Observable<T>): Observable<T>

    fun filter(predicate: (value: T) -> Boolean): Observable<T>
    fun first(predicate: (value: T) -> Boolean): Observable<T>

    /**
     * Applies a given `project` function to each value emitted by the source
     * Observable, and emits the resulting values as an Observable.
     */
    fun <R> map(project: (value: T) -> R): Observable<R>

    /**
     * Applies a given `project` function to each value emitted by the source
     * Observable, and emits the resulting values as an Observable.
     */
    fun <R> map(project: (value: T, index: Int) -> R): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an Observable, and then merging those resulting  Observables and
     * emitting the results of this merger.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     */
    fun <R> mergeMap(project: (value: T) -> Subscribable<R>): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an Observable, and then merging those resulting  Observables and
     * emitting the results of this merger.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     * The arguments passed to this function are:
     * 1. value: the value that came from the source
     * 2. index: the index of the value that came from the source
     */
    fun <R> mergeMap(project: (value: T, index: Int) -> Subscribable<R>): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an Observable, and then merging those resulting  Observables and
     * emitting the results of this merger.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     * @param resultSelector A function to produce the value on the output Observable based on the values and the
     * indices of the source (outer) emission and the inner Observable emission. The arguments passed to this function
     * are:
     * 1. outerValue: the value that came from the source
     * 2. innerValue: the value that came from the projected Observable
     */
    fun <I, R> mergeMap(project: (value: T) -> Subscribable<I>,
                        resultSelector: (outerValue: T,
                                         innerValue: I) -> R,
                        concurrent: Int = definedExternally): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an Observable, and then merging those resulting  Observables and
     * emitting the results of this merger.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     * @param resultSelector A function to produce the value on the output Observable based on the values and the
     * indices of the source (outer) emission and the inner Observable emission. The arguments passed to this function
     * are:
     * 1. outerValue: the value that came from the source
     * 2. innerValue: the value that came from the projected Observable
     * 3. outerIndex: the "index" of the value that came from the source
     * 4. innerIndex: the "index" of the value from the projected Observable
     */
    fun <I, R> mergeMap(project: (value: T, index: Int) -> Subscribable<I>,
                        resultSelector: (outerValue: T,
                                         innerValue: I,
                                         outerIndex: Int,
                                         innerIndex: Int) -> R,
                        concurrent: Int = definedExternally): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable, emitting values only from
     * the most recently projected Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an (so-called "inner") Observable. Each time it observes one of
     * these inner Observables, the output Observable begins emitting the items emitted by that inner Observable. When a
     * new inner Observable is emitted, `switchMap` stops emitting items from the earlier-emitted inner Observable and
     * begins emitting items from the new one. It continues to behave like this for subsequent inner Observables.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     */
    fun <R> switchMap(project: (value: T) -> Subscribable<R>): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable, emitting values only from
     * the most recently projected Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an (so-called "inner") Observable. Each time it observes one of
     * these inner Observables, the output Observable begins emitting the items emitted by that inner Observable. When a
     * new inner Observable is emitted, `switchMap` stops emitting items from the earlier-emitted inner Observable and
     * begins emitting items from the new one. It continues to behave like this for subsequent inner Observables.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     */
    fun <R> switchMap(project: (value: T, index: Int) -> Subscribable<R>): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable, emitting values only from
     * the most recently projected Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an (so-called "inner") Observable. Each time it observes one of
     * these inner Observables, the output Observable begins emitting the items emitted by that inner Observable. When a
     * new inner Observable is emitted, `switchMap` stops emitting items from the earlier-emitted inner Observable and
     * begins emitting items from the new one. It continues to behave like this for subsequent inner Observables.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     * @param resultSelector A function to produce the value on the output Observable based on the values and the
     * indices of the source (outer) emission and the inner Observable emission. The arguments passed to this function
     * are:
     * 1. outerValue: the value that came from the source
     * 2. innerValue: the value that came from the projected Observable
     */
    fun <I, R> switchMap(project: (value: T) -> Subscribable<I>,
                         resultSelector: (outerValue: T,
                                          innerValue: I) -> R): Observable<R>

    /**
     * Projects each source value to an Observable which is merged in the output Observable, emitting values only from
     * the most recently projected Observable.
     *
     * Returns an Observable that emits items based on applying a function that you supply to each item emitted by the
     * source Observable, where that function returns an (so-called "inner") Observable. Each time it observes one of
     * these inner Observables, the output Observable begins emitting the items emitted by that inner Observable. When a
     * new inner Observable is emitted, `switchMap` stops emitting items from the earlier-emitted inner Observable and
     * begins emitting items from the new one. It continues to behave like this for subsequent inner Observables.
     *
     * @param project A function that, when applied to an item emitted by the source Observable, returns an Observable.
     * @param resultSelector A function to produce the value on the output Observable based on the values and the
     * indices of the source (outer) emission and the inner Observable emission. The arguments passed to this function
     * are:
     * 1. outerValue: the value that came from the source
     * 2. innerValue: the value that came from the projected Observable
     * 3. outerIndex: the "index" of the value that came from the source
     * 4. innerIndex: the "index" of the value from the projected Observable
     */
    fun <I, R> switchMap(project: (value: T, index: Int) -> Subscribable<I>,
                         resultSelector: (outerValue: T,
                                          innerValue: I,
                                          outerIndex: Int,
                                          innerIndex: Int) -> R): Observable<R>

    open fun <R> lift(operator: Operator<T, R>): Subject<R>

    companion object {

        /**
         * Creates a new Observable, that will execute the specified function when
         * an [Observer] subscribes to it.
         *
         * `create` converts an `onSubscription` function to an actual Observable.
         * Whenever someone subscribes to that Observable, the function will be
         * called with an [Observer] instance as a first and only parameter.
         * `onSubscription` should then invoke the Observers `next`, `error` and
         * `complete` methods.
         *
         * Calling `next` with a value will emit that value to the observer. Calling
         * `complete` means that Observable finished emitting and will not do anything
         * else. Calling `error` means that something went wrong - value passed to
         * error method should provide details on what exactly happened.
         *
         * A well-formed Observable can emit as many values as it needs via `next`
         * method, but `complete` and `error` methods can be called only once and
         * nothing else can be called thereafter. If you try to invoke `next`,
         * `complete` or `error` methods after created Observable already completed
         * or ended with an error, these calls will be ignored to preserve so called
         * Observable Contract. Note that you are not required to call complete at
         * any point - it is perfectly fine to create an Observable that never ends,
         * depending on your needs.
         *
         * `onSubscription` can optionally return either a function or an object with
         * `unsubscribe` method. In both cases function or method will be called when
         * subscription to Observable is being cancelled and should be used to clean
         * up all resources. So, for example, if you are using `setTimeout` in your
         * custom Observable, when someone unsubscribes, you can clear planned timeout,
         * so that it does not fire needlessly and browser (or other environment) does
         * not waste computing power on timing event that no one will listen to anyways.
         *
         * Most of the times you should not need to use `create`, because existing
         * operators allow you to create an Observable for most of the use cases. That
         * being said, `create` is low-level mechanism allowing you to create any
         * Observable, if you have very specific needs.
         */
        fun <T> create(onSubscription: (observer: Observer<T>) -> Any): Observable<T>

        /**
         * Creates an Observable that emits no items to the Observer and immediately
         * emits a complete notification.
         */
        fun <T> empty(): Observable<T>

        /**
         * TODO
         */
        fun <T> from(subscribable: Subscribable<T>): Observable<T>

        /**
         * TODO
         */
        fun <T> from(values: Array<T>): Observable<T>

        /**
         * Creates an Observable that emits some values you specify as arguments,
         * immediately one after the other, and then emits a complete notification.
         *
         * This static operator is useful for creating a simple Observable that
         * only emits the arguments given, and the complete notification thereafter.
         * It can be used for composing with other Observables, such as with [concat].
         * By default, it uses a `null` IScheduler, which means the `next` notifications
         * are sent synchronously, although with a different IScheduler it is possible
         * to determine when those notifications will be delivered.
         */
        fun <T> of(vararg values: T): Observable<T>
    }
}