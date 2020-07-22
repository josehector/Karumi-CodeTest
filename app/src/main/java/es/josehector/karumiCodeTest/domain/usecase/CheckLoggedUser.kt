package es.josehector.karumiCodeTest.domain.usecase

import es.josehector.karumiCodeTest.data.repository.UserLoginRepository
import javax.inject.Inject

class CheckLoggedUser @Inject constructor(private val userLoginRepository: UserLoginRepository) {
    fun execute() : Boolean {
        return (userLoginRepository.getAuthenticationToken() != null)
    }
}
