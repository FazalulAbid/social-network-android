package com.fifty.socialnetwork.featureprofile.domain.usecase


data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val getSkills: GetSkillsUseCase,
    val updateProfile: UpdateProfileUseCase,
    val setSkillSelected: SetSkillSelectedUseCase,
    val getPostsForProfile: GetPostsForProfileUseCase
)
