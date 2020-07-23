package es.josehector.karumiCodeTest.ui.view

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
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
        clearSharedPreferences()
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
        onView(withId(R.id.bt_logout)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowLoginUnsuccess() {
        onView(withId(R.id.et_userName)).perform(replaceText(VALID_USERNAME), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(replaceText("pepe"), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())

        onView(withId(R.id.tv_error)).check(matches(withText(R.string.msg_login_incorrect)))
    }

    private fun getEditorSharedPreferences(): SharedPreferences.Editor {
        val targetContext: Context = getInstrumentation().targetContext
        return targetContext.getSharedPreferences("USER_LOGIN_PREFRENCES", Context.MODE_PRIVATE)
            .edit()
    }

    private fun clearSharedPreferences() {
        val editor =
            getEditorSharedPreferences()
        editor.clear()
        editor.commit()
    }
}
