package es.josehector.karumiCodeTest.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.josehector.karumiCodeTest.R
import es.josehector.karumiCodeTest.ui.presenter.LogoutPresenter
import kotlinx.android.synthetic.main.logout_activity.*
import javax.inject.Inject

@AndroidEntryPoint
class LogoutActivity : AppCompatActivity(), LogoutPresenter.View {

    @Inject
    lateinit var presenter: LogoutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logout_activity)

        initializePresenter()
        initializeLogoutButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initializePresenter() {
        presenter.attachView(this)
    }

    private fun initializeLogoutButton() {
        bt_logout.setOnClickListener {
            presenter.doLogout()
        }
    }
}