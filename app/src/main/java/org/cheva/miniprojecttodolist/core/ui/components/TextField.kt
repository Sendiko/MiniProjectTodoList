package org.cheva.miniprojecttodolist.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 *
 * Custom OutlinedTextField that comes with the starter code.
 *
 * @param modifier Modifier yang akan diberikan ke OutlinedTextField.
 * @param label String untuk text yang ditampilkan diatas OutlinedTextField/
 * @param value String yang akan ditampilkan dalam OutlinedTextField.
 * @param onValueChange Fungsi yang akan dipanggil ketika value berubah.
 * @param isError Boolean yang menentukan apakah OutlinedTextField memiliki error atau tidak.
 * @param hint String yang akan ditampilkan sebagai hint jika value kosong.
 * @param supportingText text kecil yang akan ditampilkan dibawah OutlinedTextField.
 * @param leadingIcon Icon yang akan ditampilkan di sebelah kiri dari OutlinedTextField.
 * @param trailingIcon Icon yang akan ditampilkan di sebelah kanan dari OutlinedTextField.
 * @param singleLine Parameter yang membuat textfield menjadi single line.
 * @param maxLines Jumlah maksimal baris yang dapat ditampilkan dalam textfield.
 * @param minLines Jumlah minimal baris yang dapat ditampilkan dalam textfield.
 * @param readOnly Parameter yang menentukan apakah textfield hanya bisa dibaca atau tidak.
 * @param enabled Parameter yang menentukan apakah textfield aktif atau tidak.
 * @param keyboardType Jenis keyboard yang akan ditampilkan dalam textfield.
 * @param imeAction Jenis aksi untuk tombol Enter.
 * @param action Aksi yang akan diambil ketika tombol keyboard ditekan.
 *
* */
@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    hint: String? = null,
    supportingText: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    action: KeyboardActions = KeyboardActions(),
    focusRequester: FocusRequester = FocusRequester()
) {
    Column(
        modifier = modifier.focusRequester(focusRequester),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            placeholder = {
                if (hint != null) {
                    Text(text = hint)
                }
            },
            supportingText = {
                if (supportingText != null) {
                    Text(text = supportingText)
                }
            },
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            readOnly = readOnly,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = action,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
        )
    }
}