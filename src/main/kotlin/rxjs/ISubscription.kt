@file:JsModule("rxjs")

package rxjs

external interface ISubscription : AnonymousSubscription {

    /**
     * A flag to indicate whether this Subscription has already been unsubscribed.
     */
    val closed: Boolean?
}