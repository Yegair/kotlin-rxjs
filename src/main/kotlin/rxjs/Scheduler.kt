@file:JsModule("rxjs")

package rxjs

external interface IScheduler {

    fun now(): Int

    /**
     * Schedules a function, `work`, for execution. May happen at some point in the future, according to the `delay`
     * parameter, if specified. May be passed some context object, `state`, which will be passed to the work function.
     *
     * @param work A function representing a task, or some unit of work to be executed by the Scheduler.
     *
     * @return A subscription in order to be able to unsubscribe the scheduled work.
     */
    fun <T> schedule(work: (state: T?) -> Subscription?): Subscription

    /**
     * Schedules a function, `work`, for execution. May happen at some point in the future, according to the `delay`
     * parameter, if specified. May be passed some context object, `state`, which will be passed to the work function.
     *
     * @param work A function representing a task, or some unit of work to be executed by the Scheduler.
     *
     * @param delay Time to wait before executing the work, where the time unit is implicit and defined by the Scheduler
     * itself.
     *
     * @return A subscription in order to be able to unsubscribe the scheduled work.
     */
    fun <T> schedule(work: (state: T?) -> Subscription?, delay: Int): Subscription

    /**
     * Schedules a function, `work`, for execution. May happen at some point in the future, according to the `delay`
     * parameter, if specified. May be passed some context object, `state`, which will be passed to the work function.
     *
     * @param work A function representing a task, or some unit of work to be executed by the Scheduler.
     *
     * @param delay Time to wait before executing the work, where the time unit is implicit and defined by the Scheduler
     * itself.
     *
     * @param state Some contextual data that the work function uses when called by the Scheduler.
     *
     * @return A subscription in order to be able to unsubscribe the scheduled work.
     */
    fun <T> schedule(work: (state: T?) -> Subscription?, delay: Int, state: T): Subscription
}

external open class Scheduler : IScheduler {

    /**
     * @see IScheduler.now
     */
    override fun now(): Int

    /**
     * @see IScheduler.schedule
     */
    override fun <T> schedule(work: (state: T?) -> Subscription?): Subscription

    /**
     * @see IScheduler.schedule
     */
    override fun <T> schedule(work: (state: T?) -> Subscription?, delay: Int): Subscription

    /**
     * @see IScheduler.schedule
     */
    override fun <T> schedule(work: (state: T?) -> Subscription?, delay: Int, state: T): Subscription

    /**
     * A getter method that returns a number representing the current time (at the time this function was called)
     * according to the scheduler's own internal clock.
     *
     * @return A number that represents the current time. May or may not have a relation to wall-clock time. May or may
     * not refer to a time unit (e.g. milliseconds).
     */
    val now: () -> Int

    companion object {

        /**
         * @see IScheduler.now
         */
        val now: () -> Int

        /**
         * Schedules on a queue in the current event frame (trampoline scheduler). Use this for iteration operations.
         */
        val queue: Scheduler

        /**
         * Schedules on the micro task queue, which uses the fastest transport mechanism available, either Node.js'
         * `process.nextTick()` or Web Worker MessageChannel or `setTimeout` or others. Use this for asynchronous
         * conversions.
         */
        val asap: Scheduler

        /**
         * Schedules work with `setInterval`. Use this for time-based operations.
         */
        val async: Scheduler
    }
}