package com.fifty.socialnetwork.di

import android.provider.ContactsContract.Profile
import com.fifty.socialnetwork.featureprofile.data.remote.ProfileApi
import com.fifty.socialnetwork.featureprofile.data.repository.ProfileRepositoryImpl
import com.fifty.socialnetwork.featureprofile.domain.repository.ProfileRepository
import com.fifty.socialnetwork.featureprofile.domain.usecase.GetProfileUseCase
import com.fifty.socialnetwork.featureprofile.domain.usecase.ProfileUseCases
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
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .baseUrl(ProfileApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(api: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository)
        )
    }
}