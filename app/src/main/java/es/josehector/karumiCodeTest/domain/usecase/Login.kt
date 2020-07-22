package es.josehector.karumiCodeTest.domain.usecase

import es.josehector.karumiCodeTest.data.repository.UserLoginRepository
import es.josehector.karumiCodeTest.data.service.LoginService
import javax.inject.Inject

class Login @Inject constructor(private val loginService: LoginService, private val userLoginRepository: UserLoginRepository) {
    fun execute(userName: String, password: String): String? {
        val token = loginService.doLogin(userName, password)
        userLoginRepository.saveAuthenticationToken(token)
        return token
    }
}
