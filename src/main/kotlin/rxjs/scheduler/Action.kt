@file:JsModule("rxjs")

package rxjs.scheduler

import rxjs.Scheduler
import rxjs.Subscription

external open class Action<in T>(scheduler: Scheduler, work: (state: T?) -> Unit) : Subscription {

    /**
     * Schedules this action on its parent Scheduler for execution. May be passed some context object, `state`. May
     * happen at some point in the future, according to the `delay` parameter, if specified.
     *
     * @param state Some contextual data that the `work` function uses when called by the Scheduler.
     * @param delay Time to wait before executing the work, where the time unit is implicit and defined by the
     * Scheduler.
     */
    fun schedule(state: T?, delay: Int?): Subscription
}