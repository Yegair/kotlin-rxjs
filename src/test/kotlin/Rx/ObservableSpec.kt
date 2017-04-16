package Rx

import jasmine.describe
import jasmine.it

@Suppress("unused")
private val spec = describe("Observable", {

    it("should run", { done ->

        val observable = Observable.from(arrayOf(1, 2, 3))

        observable.subscribe(
                next = { values -> console.log(values) },
                error = { err -> console.error(err) },
                complete = { done() }
        )
    })
})
