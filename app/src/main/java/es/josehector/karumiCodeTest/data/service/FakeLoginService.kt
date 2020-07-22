package es.josehector.karumiCodeTest.data.service

/**
 * Fake login services.
 * Simulate a request network (3 seconds delay).
 * The returned toke will be current time in milliseconds.
 */
class FakeLoginService : LoginService {
    companion object {
        private const val ALLOWED_EMAIL = "admin@admin.es"
        private const val ALLOWED_PASSWORD = "admin"
    }

    override fun doLogin(userName: String, password: String): String? {
        Thread.sleep(3000L)
        if (ALLOWED_EMAIL.equals(userName) &&
            ALLOWED_PASSWORD.equals(password)
        ) {
            return System.currentTimeMillis().toString()
        }
        return null
    }
}
