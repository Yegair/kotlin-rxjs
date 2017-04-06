@file:JsModule("Rx")
@file:JsNonModule

package rx

external open class Subscription(unsubscribe: () -> Unit) : AnonymousSubscription {

    open val closed: Boolean

    fun add(teardown: AnonymousSubscription): Subscription
    fun add(teardown: () -> Unit): Subscription
    fun remove(subscription: Subscription): Unit
    override fun unsubscribe(): Unit
}
