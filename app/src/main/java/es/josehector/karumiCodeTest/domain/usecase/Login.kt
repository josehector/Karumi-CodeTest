package es.josehector.karumiCodeTest.domain.usecase

import es.josehector.karumiCodeTest.data.service.LoginService
import javax.inject.Inject

class Login @Inject constructor(private val loginService: LoginService) {
    fun execute(userName: String, password: String): String? {
        return loginService.doLogin(userName, password)
    }
}
