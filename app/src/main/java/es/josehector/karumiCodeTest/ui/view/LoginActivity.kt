package es.josehector.karumiCodeTest.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.josehector.karumiCodeTest.R
import es.josehector.karumiCodeTest.ui.presenter.LoginPresenter
import kotlinx.android.synthetic.main.login_activity.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), LoginPresenter.View {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        initializePresenter()
        initializeLoginButton()
    }

    override fun showLoading() {
        pb_login.visibility = View.VISIBLE
        bt_login.isEnabled = false
        et_userName.isEnabled = false
        et_password.isEnabled = false
    }

    override fun hideLoading() {
        pb_login.visibility = View.GONE
        bt_login.isEnabled = true
        et_userName.isEnabled = true
        et_password.isEnabled = true
    }

    override fun showEmailInvalid() {
        showError(getString(R.string.msg_invalid_username))
    }

    override fun showLogInErrorMessage() {
        showError(getString(R.string.msg_login_incorrect))
    }

    override fun navigateToLogout() {
        // TODO: should go to logout screen
        Toast.makeText(this, getString(R.string.msg_login_correct), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun showError(errorText: String) {
        tv_error.text = errorText
        tv_error.visibility = View.VISIBLE
    }

    private fun hideError() {
        tv_error.text = ""
        tv_error.visibility = View.GONE
    }

    private fun initializePresenter() {
        presenter.attachView(this)
    }

    private fun initializeLoginButton() {
        bt_login.setOnClickListener {
            hideError()
            val userName = et_userName.text.toString()
            val password = et_password.text.toString()
            presenter.doLogin(userName, password)
        }
    }
}
