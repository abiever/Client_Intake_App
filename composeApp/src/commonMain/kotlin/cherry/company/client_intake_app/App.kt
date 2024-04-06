package cherry.company.client_intake_app

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import client_intake_app.composeapp.generated.resources.*
import cherry.company.client_intake_app.theme.AppTheme
import cherry.company.client_intake_app.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() = AppTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.cyclone),
            fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
            style = MaterialTheme.typography.displayLarge
        )

        var isDark by LocalThemeIsDark.current
        val icon = remember(isDark) {
            if (isDark) Res.drawable.ic_light_mode
            else Res.drawable.ic_dark_mode
        }

        ElevatedButton(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
            onClick = { isDark = !isDark },
            content = {
                Icon(vectorResource(icon), contentDescription = null)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Switch Theme")
            }
        )

        //Just playing with some new buttons below
        val count = remember { mutableStateOf(0) }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                count.value++
            }
        ) {
            Text(if (count.value == 0) "Hello World!" else "You've clicked ${count.value} times!")
        }

        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                count.value = 0
            }) {
            Text("Reset")
        }

        //Trying to create some text fields
        var firstName by remember { mutableStateOf(("")) }
        TextField(
            value = firstName,
            singleLine = true,
            onValueChange = { firstName = it },
            modifier = Modifier.padding(20.dp)
        )

        var lastName by remember { mutableStateOf(("")) }
        TextField(
            value = lastName,
            singleLine = true,
            onValueChange = { lastName = it },
            modifier = Modifier.padding(20.dp)
        )

        //TODO: This would probably be better as "birthday", as age will change with time
        var age by remember { mutableStateOf(("")) }
        TextField(
            value = age,
            singleLine = true,
            onValueChange = { value ->
                if (value.toInt() < 120) {
                    age = value.filter { it.isDigit()}
                }
            },
            modifier = Modifier.padding(20.dp)
            //ISSUE: Doesn't allow for backspacing to empty field?
        )

        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        DatePicker(state = state, modifier = Modifier.padding(16.dp))
        Text("Entered date timestamp: ${state.selectedDateMillis ?: "no input"}")

        //Use this button to "save" the name to a field on the app for the time being
        val isClicked = remember { mutableStateOf(false) }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                isClicked.value = !isClicked.value
            }) {
            Text(if (isClicked.value) firstName + lastName + age else "Save Client")
        }

        //Todo: add dropdown and radio buttons
        //TODO: Create a basic "client" class and see if I can save basic info to it, then recall it
            //The data can be saved in the app or on the hosting machine itself for now
    }
}

internal expect fun openUrl(url: String?)