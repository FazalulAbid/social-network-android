package com.fifty.socialnetwork.core.util

interface Paginator {
    suspend fun loadNextItems()
}