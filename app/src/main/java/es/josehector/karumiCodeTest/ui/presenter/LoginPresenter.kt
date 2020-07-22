package es.josehector.karumiCodeTest.ui.presenter

import es.josehector.karumiCodeTest.data.repository.UserLoginRepository
import es.josehector.karumiCodeTest.domain.usecase.Login
import es.josehector.karumiCodeTest.util.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val login: Login,
    private val userLoginRepository: UserLoginRepository
) : CoroutineScope by MainScope() {
    var view: View? = null

    fun attachView(view: View) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun doLogin(userName: String, password: String) {
        launch {
            if (!isEmailValid(userName)) {
                view?.showEmailInvalid()
            } else {
                view?.showLoading()
                EspressoIdlingResource.increment() // Only for testing use.
                val token =
                    withContext(Dispatchers.IO) {
                        login.execute(
                            userName,
                            password
                        )
                    }
                EspressoIdlingResource.decrement() // Only for testing use.
                when (token) {
                    null -> view?.showLogInErrorMessage()
                    else -> {
                        userLoginRepository.saveAuthenticationToken(token)
                        view?.navigateToLogout()
                    }
                }
                view?.hideLoading()
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showEmailInvalid()
        fun showLogInErrorMessage()
        fun navigateToLogout()
    }
}
