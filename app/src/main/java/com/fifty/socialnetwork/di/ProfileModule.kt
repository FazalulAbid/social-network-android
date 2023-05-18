package com.fifty.socialnetwork.di

import com.fifty.socialnetwork.core.domain.usecase.ToggleFollowStateForUserUseCase
import com.fifty.socialnetwork.featurepost.data.remote.PostApi
import com.fifty.socialnetwork.featureprofile.data.remote.ProfileApi
import com.fifty.socialnetwork.core.data.repository.ProfileRepositoryImpl
import com.fifty.socialnetwork.core.domain.repository.ProfileRepository
import com.fifty.socialnetwork.featureprofile.domain.usecase.*
import com.google.gson.Gson
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
    fun provideProfileRepository(
        profileApi: ProfileApi,
        postApi: PostApi,
        gson: Gson
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, postApi, gson)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository),
            getSkills = GetSkillsUseCase(repository),
            updateProfile = UpdateProfileUseCase(repository),
            setSkillSelected = SetSkillSelectedUseCase(),
            getPostsForProfile = GetPostsForProfileUseCase(repository),
            searchUser = SearchUserUseCase(repository),
            toggleFollowStateForUser = ToggleFollowStateForUserUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun providesToggleFollowForUserUseCase(repository: ProfileRepository): ToggleFollowStateForUserUseCase {
        return ToggleFollowStateForUserUseCase(repository)
    }
}