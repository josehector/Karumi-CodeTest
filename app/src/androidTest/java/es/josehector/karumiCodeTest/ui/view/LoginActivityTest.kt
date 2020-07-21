package es.josehector.karumiCodeTest.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import es.josehector.karumiCodeTest.R
import es.josehector.karumiCodeTest.ToastMatcher
import es.josehector.karumiCodeTest.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class LoginActivityTest {

    companion object {
        private const val VALID_USERNAME = "admin@admin.es"
        private const val VALID_PASSWORD = "admin"
    }

    @Before
    fun setUp() {
        ActivityScenario.launch(LoginActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun shouldShowLoginScreen() {
        onView(withId(R.id.iv_logo)).check(matches(isDisplayed()))
        onView(withId(R.id.et_userName)).check(matches(isDisplayed()))
        onView(withId(R.id.et_password)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_login)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowEmailInvalidMsgWhenIncompleteEmail() {
        onView(withId(R.id.et_userName)).perform(replaceText("prueba@"), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())

        onView(withId(R.id.tv_error)).check(matches(withText(R.string.msg_invalid_username)))
    }

    @Test
    fun shouldShowEmailInvalidMsgWhenEmptyEmail() {
        onView(withId(R.id.et_userName)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())

        onView(withId(R.id.tv_error)).check(matches(withText(R.string.msg_invalid_username)))
    }

    @Test
    fun shouldShowLoginSuccess() {
        onView(withId(R.id.et_userName)).perform(replaceText(VALID_USERNAME), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(replaceText(VALID_PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())

        onView(withText(R.string.msg_login_correct)).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowLoginUnsuccess() {
        onView(withId(R.id.et_userName)).perform(replaceText(VALID_USERNAME), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(replaceText("pepe"), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())

        onView(withId(R.id.tv_error)).check(matches(withText(R.string.msg_login_incorrect)))
    }
}
