package org.cheva.miniprojecttodolist.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object RegisterScreen

@Serializable
object LoginScreen

@Serializable
data class DashboardScreen(
    val username: String,
    val token: String,
)

@Serializable
object TodoScreen