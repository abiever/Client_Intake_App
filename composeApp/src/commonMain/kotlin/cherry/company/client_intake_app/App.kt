package cherry.company.client_intake_app

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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

@Composable
internal fun App() = AppTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
        var name by remember { mutableStateOf(("")) }
        TextField(
            value = name,
            singleLine = true,
            onValueChange = { name = it },
            modifier = Modifier.padding(20.dp)
        )

        //Use this button to "save" the name to a field on the app for the time being
        val isClicked = remember { mutableStateOf(false) }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                isClicked.value = !isClicked.value
            }) {
            Text(if (isClicked.value) name else "Save Name")
        }
    }
}

internal expect fun openUrl(url: String?)