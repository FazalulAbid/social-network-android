package com.fifty.socialnetwork.di

import android.content.SharedPreferences
import com.fifty.socialnetwork.featureauth.data.remote.AuthApi
import com.fifty.socialnetwork.featureauth.data.repository.AuthRepositoryImpl
import com.fifty.socialnetwork.featureauth.domain.repository.AuthRepository
import com.fifty.socialnetwork.featureauth.domain.usecase.AuthenticateUseCase
import com.fifty.socialnetwork.featureauth.domain.usecase.LoginUseCase
import com.fifty.socialnetwork.featureauth.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(AuthApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, sharedPreferences: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideAuthenticationUseCase(repository: AuthRepository): AuthenticateUseCase {
        return AuthenticateUseCase(repository = repository)
    }
}