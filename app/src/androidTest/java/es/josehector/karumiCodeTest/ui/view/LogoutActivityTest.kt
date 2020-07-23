package es.josehector.karumiCodeTest.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import es.josehector.karumiCodeTest.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class LogoutActivityTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(LogoutActivity::class.java)
    }

    @Test
    fun shouldShowLogoutScreen() {
        onView(withId(R.id.iv_logo))
            .check(matches(isDisplayed()))
        onView(withId(R.id.bt_logout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowLoginScreenWhenClickLogoutButton() {
        onView(withId(R.id.bt_logout)).perform(click())

        onView(withId(R.id.iv_logo)).check(matches(isDisplayed()))
        onView(withId(R.id.et_userName)).check(matches(isDisplayed()))
        onView(withId(R.id.et_password)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_login)).check(matches(isDisplayed()))
    }
}
