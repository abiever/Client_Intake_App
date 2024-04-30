package cherry.company.client_intake_app.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cherry.company.client_intake_app.Client

//TODO: Eventually RENAME this to "Dashboard" or something like that?
//This is going to be the main spot where clients are tracked/created/etc.

data class ClientListScreen(val clientsList: MutableList<Client>) : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            clientsList.forEach { client ->
//                Button(
//                    onClick = { onClientClicked(client) },
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text("${client.getFirstName()} ${client.getLastName()}")
//                }
//            }
            if (clientsList.isNotEmpty()) {
                for (client in clientsList) {
                    // Use a composable function to create the button
                    ShowMoreButton(client = client)
                }
                //TODO: The below may be able to be turned into a Composable for reusability/editability
                Button(
                    onClick = { navigator.push(NewClientFormScreen(clientsList))}
                ) {
                    Text("Create Client")
                }
            }
            if (clientsList.isEmpty()) {
                Text("You currently have no clients.")
                Button(
                    onClick = { navigator.push(NewClientFormScreen(clientsList))}
                ) {
                    Text("Create Client")
                }
            }
        }
    }


}
