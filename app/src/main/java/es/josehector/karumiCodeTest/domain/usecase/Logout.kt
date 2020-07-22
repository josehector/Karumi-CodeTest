package es.josehector.karumiCodeTest.domain.usecase

import es.josehector.karumiCodeTest.data.repository.UserLoginRepository
import javax.inject.Inject

class Logout @Inject constructor(private val userLoginRepository: UserLoginRepository) {
    fun execute() {
        userLoginRepository.saveAuthenticationToken(null)
    }
}