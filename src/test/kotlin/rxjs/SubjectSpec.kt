package rxjs

import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("unused")
class SubjectSpec {

    @Test
    fun shouldRun() {

        assertTrue {
            console.log("Subject spec running ...")
            true
        }
    }
}
