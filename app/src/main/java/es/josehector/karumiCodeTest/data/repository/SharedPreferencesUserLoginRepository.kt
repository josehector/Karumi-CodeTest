package es.josehector.karumiCodeTest.data.repository

import android.content.Context

class SharedPreferencesUserLoginRepository(context: Context) : UserLoginRepository {

    companion object {
        private const val SHARED_PREFERENCE_NAME = "USER_LOGIN_PREFRENCES"
        private const val TOKEN_NAME = "TOKEN_NAME"
    }

    private val prefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun saveAuthenticationToken(token: String?) {
        var editor = prefs.edit()
        editor.putString(TOKEN_NAME, token)
        editor.apply()
    }

    override fun getAuthenticationToken(): String? {
        return prefs
            .getString(TOKEN_NAME, null)
    }
}
