package cherry.company.client_intake_app.composables
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import cherry.company.client_intake_app.Client
//
//@Composable
//fun ClientListScreen(
//    clients: List<Client>,
//    onClientClicked: (Client) -> Unit
//) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        clients.forEach { client ->
//            Button(
//                onClick = { onClientClicked(client) },
//                modifier = Modifier.padding(8.dp)
//            ) {
//                Text("${client.getFirstName()} ${client.getLastName()}")
//            }
//        }
//    }
//}
