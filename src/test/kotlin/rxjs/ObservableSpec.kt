package rxjs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.fail


@Suppress("unused")
class ObservableSpec {

    @Test
    fun catch() {

        val observable = Observable.create<String> { observer ->
            observer.next("Foo")
            observer.error(Exception("Oops"))
            observer.complete()
        }.catch { err -> Observable.of((err as Exception).message ?: "unknown") }

        val values = mutableListOf<String>()

        observable.subscribe(
            next = { values.add(it) },
            error = { fail() },
            complete = { assertEquals(listOf("Foo", "Oops"), values) }
        )
    }

    @Test
    fun catchRetry() {

        var count = 0
        val observable = Observable.create<String> { observer ->
            observer.next("Foo")
            observer.error(Exception("Oops"))
            observer.complete()
        }.catch { err, caught ->
            when (count) {
                0 -> {
                    count++
                    caught
                }
                else -> Observable.of((err as Exception).message ?: "unknown")
            }
        }

        val values = mutableListOf<String>()

        observable.subscribe(
            next = { values.add(it) },
            error = { fail() },
            complete = { assertEquals(listOf("Foo", "Foo", "Oops"), values) }
        )
    }

    @Test
    fun create() {

        val observable = Observable.create<String> { observer ->
            observer.next("Foo")
            observer.next("Bar")
            observer.complete()
        }
        val values = mutableListOf<String>()

        observable.subscribe(
            next = { values.add(it) },
            error = { fail() },
            complete = { assertEquals(listOf("Foo", "Bar"), values) }
        )
    }

    @Test
    fun empty() {

        val observable = Observable.empty<Any>()

        observable.subscribe(
            next = { fail() },
            error = { fail() },
            complete = { }
        )
    }

    @Test
    fun fromArray() {

        val observable = Observable.from(arrayOf(1, 2, 3))
        val values = mutableListOf<Int>()

        observable.subscribe(
            next = { value ->
                values.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 2, 3), values)
            }
        )
    }

    @Test
    fun fromIntArray() {

        val observable = Observable.from(intArrayOf(1, 2, 3))
        val values = mutableListOf<Int>()

        observable.subscribe(
            next = { value ->
                values.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 2, 3), values)
            }
        )
    }

    @Test
    fun fromSubject() {

        val subject = Subject.create<Int>()

        val observable = Observable.from(subject)

        // TODO: fix test when async testing is available
        assertNotNull(observable)

//        val values = mutableListOf<Int>()
//
//        observable.subscribe(
//            next = { value ->
//                values.add(value)
//            },
//            error = { fail() },
//            complete = {
//                assertEquals(listOf(1, 2, 3), values)
//            }
//        )
//
//        subject.next(1)
//        subject.next(2)
//        subject.next(3)
//        subject.complete()
    }

    @Test
    fun ofVarargs() {

        val observable = Observable.of(1, 2, 3)
        val values = mutableListOf<Int>()

        observable.subscribe(
            next = { value ->
                values.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 2, 3), values)
            }
        )
    }

    @Test
    fun map() {

        val observable = Observable.of(1, 2, 3).map { value -> value * 2 }
        val values = mutableListOf<Int>()

        observable.subscribe(
            next = { value ->
                values.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(2, 4, 6), values)
            }
        )
    }

    @Test
    fun mapIndexed() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .map { value, index ->
                value + index
            }

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 3, 5), result)
            }
        )
    }

    @Test
    fun mergeMap() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .mergeMap { value ->
                Observable.of(value, value * 2)
            }

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 2, 2, 4, 3, 6), result)
            }
        )
    }

    @Test
    fun mergeMapIndexed() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .mergeMap { value, index ->
                Observable.of(value, value + index)
            }

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 1, 2, 3, 3, 5), result)
            }
        )
    }

    @Test
    fun mergeMapSelect() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .mergeMap(
                { value -> Observable.of(value, value * 2) },
                { outerValue, innerValue -> outerValue + innerValue }
            )

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(2, 3, 4, 6, 6, 9), result)
            }
        )
    }

    @Test
    fun mergeMapSelectIndexed() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .mergeMap(
                { value, index -> Observable.of(value, value + index) },
                { outerValue, innerValue, outerIndex, innerIndex ->
                    outerValue + innerValue + outerIndex + innerIndex
                }
            )

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(2, 3, 5, 7, 8, 11), result)
            }
        )
    }

    @Test
    fun switchMap() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .switchMap { value ->
                Observable.of(value, value * 2)
            }

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 2, 2, 4, 3, 6), result)
            }
        )
    }

    @Test
    fun switchMapIndexed() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .switchMap { value, index ->
                Observable.of(value, value + index)
            }

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(1, 1, 2, 3, 3, 5), result)
            }
        )
    }

    @Test
    fun switchMapSelect() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .switchMap(
                { value -> Observable.of(value, value * 2) },
                { outerValue, innerValue -> outerValue + innerValue }
            )

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(2, 3, 4, 6, 6, 9), result)
            }
        )
    }

    @Test
    fun switchMapSelectIndexed() {

        val result = mutableListOf<Int>()

        val observable = Observable.of(1, 2, 3)
            .switchMap(
                { value, index -> Observable.of(value, value + index) },
                { outerValue, innerValue, outerIndex, innerIndex ->
                    outerValue + innerValue + outerIndex + innerIndex
                }
            )

        observable.subscribe(
            next = { value ->
                result.add(value)
            },
            error = { fail() },
            complete = {
                assertEquals(listOf(2, 3, 5, 7, 8, 11), result)
            }
        )
    }
}
