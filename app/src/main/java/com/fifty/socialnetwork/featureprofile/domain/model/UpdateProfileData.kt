package com.fifty.socialnetwork.featureprofile.domain.model

data class UpdateProfileData(
    val username: String,
    val bio: String,
    val gitHubUrl: String,
    val instagramUrl: String,
    val linkedInUrl: String,
    val skills: List<Skill>,
)
