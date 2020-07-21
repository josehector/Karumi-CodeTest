package es.josehector.karumiCodeTest.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * This object only will be use for testing propose in order to manage long run task.
 *
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}
