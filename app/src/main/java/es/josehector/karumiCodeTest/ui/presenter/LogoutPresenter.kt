package es.josehector.karumiCodeTest.ui.presenter

import es.josehector.karumiCodeTest.domain.usecase.Logout
import javax.inject.Inject

class LogoutPresenter @Inject constructor(private val logout: Logout) {
    var view: View? = null

    fun attachView(view: View) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun doLogout() {
        logout.execute()
        view?.navigateToLogin()
    }

    interface View {
        fun navigateToLogin()
    }
}
