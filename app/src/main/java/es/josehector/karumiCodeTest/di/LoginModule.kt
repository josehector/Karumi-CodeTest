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
import es.josehector.karumiCodeTest.domain.usecase.Login
import es.josehector.karumiCodeTest.ui.presenter.LoginPresenter
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LoginModule {

    @Provides
    fun provideLoginPresenter(login: Login, userLoginRepository: UserLoginRepository): LoginPresenter {
        return LoginPresenter(login, userLoginRepository)
    }

    @Provides
    fun provideLoginUseCase(loginService: LoginService): Login {
        return Login(loginService)
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
