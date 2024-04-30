package cherry.company.client_intake_app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cherry.company.client_intake_app.composables.ShowMoreButton
import client_intake_app.composeapp.generated.resources.*
import cherry.company.client_intake_app.theme.AppTheme
import cherry.company.client_intake_app.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.vectorResource
import cherry.company.client_intake_app.composables.CheckableRow
import cherry.company.client_intake_app.composables.ClientListScreen
import cherry.company.client_intake_app.composables.DarkModeButton
import cherry.company.client_intake_app.composables.NewClientFormScreen


@Composable
internal fun App() = AppTheme {

    val clientsList = remember { mutableStateListOf<Client>() } //

    //Insert row here?

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cherry Healing Center", //This could be changed to something less hard-coded?
            fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
            style = MaterialTheme.typography.displayLarge
        )

        //Figure out how to make this as a part of a "menu bar" or something so that its less obtrusive and more 'reusable'
        //DarkModeButton()

        //Navigator(NewClientFormScreen(clientsList))

        val screens = listOf(NewClientFormScreen(clientsList), ClientListScreen(clientsList))

        Navigator(screens)

    }
}

internal expect fun openUrl(url: String?)