package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.core.domain.usecase.ToggleFollowStateForUserUseCase


data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val getSkills: GetSkillsUseCase,
    val updateProfile: UpdateProfileUseCase,
    val setSkillSelected: SetSkillSelectedUseCase,
    val getPostsForProfile: GetPostsForProfileUseCase,
    val searchUser: SearchUserUseCase,
    val toggleFollowStateForUser: ToggleFollowStateForUserUseCase
)
