package es.josehector.karumiCodeTest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import es.josehector.karumiCodeTest.data.repository.SharedPreferencesUserLoginRepository
import es.josehector.karumiCodeTest.data.repository.UserLoginRepository
import es.josehector.karumiCodeTest.data.service.FakeLoginService
import es.josehector.karumiCodeTest.data.service.LoginService
import es.josehector.karumiCodeTest.domain.usecase.CheckLoggedUser
import es.josehector.karumiCodeTest.domain.usecase.Login
import es.josehector.karumiCodeTest.domain.usecase.Logout
import es.josehector.karumiCodeTest.ui.presenter.LoginPresenter
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LoginModule {

    @Provides
    fun provideLoginPresenter(login: Login, checkLoggedUser: CheckLoggedUser): LoginPresenter {
        return LoginPresenter(login, checkLoggedUser)
    }

    @Provides
    fun provideLoginUseCase(loginService: LoginService, userLoginRepository: UserLoginRepository): Login {
        return Login(loginService, userLoginRepository)
    }

    @Provides
    fun provideLogoutUseCase(userLoginRepository: UserLoginRepository): Logout {
        return Logout(userLoginRepository)
    }

    @Provides
    fun provideCheckLoggedUser(userLoginRepository: UserLoginRepository): CheckLoggedUser {
        return CheckLoggedUser(userLoginRepository)
    }

    @Provides
    @Singleton
    fun provideLoginService(): LoginService {
        return FakeLoginService()
    }

    @Provides
    @Singleton
    fun provideUserLoginRepository(@ApplicationContext appContext: Context): UserLoginRepository {
        return SharedPreferencesUserLoginRepository(appContext)
    }
}
