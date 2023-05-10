package com.fifty.socialnetwork.featureprofile.domain.usecase

data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val getSkills: GetSkillsUseCase,
    val updateProfileUseCase: UpdateProfileUseCase
)
