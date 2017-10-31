@file:JsModule("rxjs")

package rxjs

external open class Subscription(unsubscribe: () -> Unit) : AnonymousSubscription {

    open val closed: Boolean

    fun add(teardown: AnonymousSubscription): Subscription
    fun add(teardown: () -> Unit): Subscription
    fun remove(subscription: Subscription)
    override fun unsubscribe()
}
