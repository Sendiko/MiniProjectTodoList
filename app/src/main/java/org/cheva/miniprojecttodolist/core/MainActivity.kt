package org.cheva.miniprojecttodolist.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.cheva.miniprojecttodolist.core.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.core.navigation.LoginScreen
import org.cheva.miniprojecttodolist.core.navigation.RegisterScreen
import org.cheva.miniprojecttodolist.core.navigation.TodoScreen
import org.cheva.miniprojecttodolist.core.ui.theme.MiniProjectTodoListTheme
import org.cheva.miniprojecttodolist.login.LoginScreen
import org.cheva.miniprojecttodolist.login.LoginViewModel
import org.cheva.miniprojecttodolist.register.RegisterScreen
import org.cheva.miniprojecttodolist.register.RegisterViewModel
import org.cheva.miniprojecttodolist.todo.list.presentation.DashboardScreen
import org.cheva.miniprojecttodolist.todo.list.presentation.DashboardViewModel
import org.cheva.miniprojecttodolist.todo.main.presentation.TodoScreen
import org.cheva.miniprojecttodolist.todo.main.presentation.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTodoListTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginScreen,
                    builder = {
                        composable<RegisterScreen> {
                            val viewModel = viewModel<RegisterViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            RegisterScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) }
                            )
                        }
                        composable<LoginScreen> {
                            val viewModel = viewModel<LoginViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            LoginScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) }
                            )
                        }
                        composable<DashboardScreen> {
                            val args = it.toRoute<DashboardScreen>()
                            val viewModel = viewModel<DashboardViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            DashboardScreen(
                                state = state,
                                username = args.username,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) }
                            )
                        }
                        composable<TodoScreen> {
                            val viewModel = viewModel<TodoViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            TodoScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) }
                            )
                        }
                    }
                )
            }
        }
    }
}