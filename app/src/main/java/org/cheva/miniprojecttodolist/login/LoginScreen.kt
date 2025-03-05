package org.cheva.miniprojecttodolist.login

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.ui.components.OutlinedTextField
import org.cheva.miniprojecttodolist.ui.components.SecureTextField
import org.cheva.miniprojecttodolist.ui.theme.MiniProjectTodoListTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {

    LaunchedEffect(state.successLogin) {
        if (state.successLogin)
            onNavigate("")
    }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
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
                hint = stringResource(R.string.email_hint)
            )
            Spacer(modifier = Modifier.height(8.dp))
            SecureTextField(
                label = stringResource(R.string.password_label),
                value = state.password,
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                keyboardType = KeyboardType.Password,
                isVisible = state.passwordVisible,
                onVisibilityChange = { onEvent(LoginEvent.OnPasswordVisibilityChanged(it)) },
                hint = stringResource(R.string.password_hint)
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(LoginEvent.OnLoginClicked) }
            ) {
                Text(stringResource(R.string.login_headline))
            }
        }
    }

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPrev() {
    MiniProjectTodoListTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {},
            onNavigate = {}
        )
    }
}