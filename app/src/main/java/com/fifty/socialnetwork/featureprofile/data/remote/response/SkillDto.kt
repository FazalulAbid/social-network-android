package com.fifty.socialnetwork.featureprofile.data.remote.response

import com.fifty.socialnetwork.featureprofile.domain.model.Skill
import com.google.gson.annotations.SerializedName

data class SkillDto(
    val name: String,
    val imageUrl: String
) {
    fun toSkill(): Skill {
        return Skill(
            name = name,
            imageUrl = imageUrl
        )
    }
}
