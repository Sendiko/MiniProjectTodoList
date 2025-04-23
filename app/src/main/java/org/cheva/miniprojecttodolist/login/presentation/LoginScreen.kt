package org.cheva.miniprojecttodolist.login.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.core.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.core.navigation.RegisterScreen
import org.cheva.miniprojecttodolist.core.ui.components.OutlinedTextField
import org.cheva.miniprojecttodolist.core.ui.components.ResultDialog
import org.cheva.miniprojecttodolist.core.ui.components.SecureTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    val focusRequester = FocusRequester()

    LaunchedEffect(state.successLogin) {
        if (state.successLogin) {
            onNavigate(DashboardScreen(state.name, state.token))
        }
    }
    Scaffold {
        if (state.message.isNotBlank()) {
            ResultDialog(
                isSuccess = state.successLogin,
                message = state.message,
                onDismiss = { onEvent(LoginEvent.OnDismissDialog) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.login_image),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.weight(1f)
                    .padding(48.dp)
            )
            Text(
                text = stringResource(R.string.login_headline),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.login_hint),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                label = stringResource(R.string.email_label),
                value = state.email,
                onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
                keyboardType = KeyboardType.Email,
                hint = stringResource(R.string.email_hint),
                imeAction = ImeAction.Next,
                action = KeyboardActions(
                    onNext = { focusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            SecureTextField(
                modifier = Modifier.focusRequester(focusRequester),
                label = stringResource(R.string.password_label),
                value = state.password,
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                keyboardType = KeyboardType.Password,
                isVisible = state.passwordVisible,
                onVisibilityChange = { onEvent(LoginEvent.OnPasswordVisibilityChanged(it)) },
                hint = stringResource(R.string.password_hint),
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(LoginEvent.OnLoginClicked) }
            ) {
                Text(stringResource(R.string.login_headline))
            }
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate(RegisterScreen) }
            ) {
                Text(stringResource(R.string.to_register))
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen(
        state = LoginState(),
        onEvent = {},
        onNavigate = {}
    )
}