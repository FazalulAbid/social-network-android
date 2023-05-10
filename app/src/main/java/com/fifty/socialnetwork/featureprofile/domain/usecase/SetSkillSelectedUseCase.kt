package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featureprofile.domain.model.Skill
import com.fifty.socialnetwork.featureprofile.domain.util.ProfileConstants

class SetSkillSelectedUseCase {

    operator fun invoke(
        selectedSkills: List<Skill>,
        skillToToggle: Skill
    ): Resource<List<Skill>> {
        val skillInList = selectedSkills.find { it.name == skillToToggle.name }
        if(skillInList != null) {
            return Resource.Success(selectedSkills - skillInList)
        }
        return if(selectedSkills.size >= ProfileConstants.MAX_SELECTED_SKILL_COUNT) {
            Resource.Error(uiText = UiText.StringResource(R.string.error_max_skills_selected))
        } else {
            Resource.Success(selectedSkills + skillToToggle)
        }
    }
}