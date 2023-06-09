package com.fifty.socialnetwork.core.util

object Constants {
    const val SPLASH_SCREEN_DURATION = 2000L

    const val MAX_POST_DESCRIPTION_LINES = 3

    const val MIN_USERNAME_LENGTH = 3

    const val DEFAULT_PAGE_SIZE = 20

    const val MIN_PASSWORD_LENGTH = 8

    const val KEY_JWT_TOKEN = "jwt_token"
    const val KEY_USER_ID = "userId"

    const val SHARED_PREF_NAME = "shared_pref"

    const val DEBUG_BASE_URL = "http://192.168.64.127:8080/"
    const val DEBUG_WS_BASE_URL = "ws://192.168.64.127:8080/api/chat/websocket"

    const val RECONNECT_INTERVAL = 5000L
}