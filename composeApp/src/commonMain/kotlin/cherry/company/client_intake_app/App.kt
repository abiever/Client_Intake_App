package cherry.company.client_intake_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import client_intake_app.composeapp.generated.resources.*
import cherry.company.client_intake_app.theme.AppTheme
import org.jetbrains.compose.resources.Font
import cherry.company.client_intake_app.screens.ClientListScreen
import cherry.company.client_intake_app.screens.NewClientFormScreen


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

        val screens = listOf(NewClientFormScreen(clientsList), ClientListScreen(clientsList))

        Navigator(screens)

    }
}

internal expect fun openUrl(url: String?)