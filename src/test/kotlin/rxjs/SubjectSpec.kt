package rxjs

import kotlin.browser.window
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

@Suppress("unused")
class SubjectSpec {

    @Suppress("UnsafeCastFromDynamic")
    fun async(): () -> Unit {
        val window = window.asDynamic()
        val asyncFn = window.QUnit.config.current.assert.async
        return asyncFn()
    }

    @Test
    fun ctor() {

        val done = async()
        val subject = Subject<Int>()
        val result = mutableListOf<Int>()

        subject.subscribeOn(Scheduler.async)
            .subscribe(
                { result.add(it) },
                {
                    done()
                    fail()
                },
                { done() }
            )

        subject.next(1)
        subject.next(2)
        subject.next(3)
        subject.complete()

        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun create() {

        val subject = Subject.create<Int>()
        val result = mutableListOf<Int>()

        subject
            .subscribeOn(undefined)
            .subscribe { result.add(it) }
        subject.next(1)
        subject.next(2)
        subject.next(3)
        subject.complete()

        assertEquals(listOf(1, 2, 3), result)
    }
}
