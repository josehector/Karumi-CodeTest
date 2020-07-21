package es.josehector.karumiCodeTest.data.repository

interface UserLoginRepository {
    fun saveAuthenticationToken(token: String?)
    fun getAuthenticationToken(): String?
}
