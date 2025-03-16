package org.cheva.miniprojecttodolist.navigation

import kotlinx.serialization.Serializable

@Serializable
object RegisterScreen

@Serializable
object LoginScreen

@Serializable
data class DashboardScreen(val username: String)