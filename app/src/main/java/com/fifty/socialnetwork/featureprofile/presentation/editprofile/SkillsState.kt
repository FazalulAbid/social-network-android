package com.fifty.socialnetwork.featureprofile.presentation.editprofile

import com.fifty.socialnetwork.featureprofile.domain.model.Skill

data class SkillsState(
    val skills: List<Skill> = emptyList(),
    val selectedSkills: List<Skill> = emptyList()
)
