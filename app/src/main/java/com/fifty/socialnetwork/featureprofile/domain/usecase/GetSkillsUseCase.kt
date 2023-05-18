package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featureprofile.domain.model.Skill
import com.fifty.socialnetwork.core.domain.repository.ProfileRepository

class GetSkillsUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Resource<List<Skill>> {
        return repository.getSkills()
    }
}