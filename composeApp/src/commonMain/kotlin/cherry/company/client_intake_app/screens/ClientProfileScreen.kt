package cherry.company.client_intake_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cherry.company.client_intake_app.Client

data class ClientProfileScreen(val client: Client, val clientsList: MutableList<Client>) : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(client.getFirstName() + " " + client.getLastName())

            Button(
                onClick = {
                    navigator.push(HomeScreen(clientsList))
                }
            ) {
                Text("Return to Dashboard")
            }
        }
    }
}
