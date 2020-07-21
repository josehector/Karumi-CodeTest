package es.josehector.karumiCodeTest.data.service

interface LoginService {
    fun doLogin(userName: String, password: String): String?
}
