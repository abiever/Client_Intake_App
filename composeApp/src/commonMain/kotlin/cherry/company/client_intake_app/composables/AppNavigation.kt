package cherry.company.client_intake_app.composables
//
//import androidx.compose.runtime.Composable
//import cherry.company.client_intake_app.composables.ClientListScreen
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(navController, startDestination = "main") {
//        composable("main") {
//            // Your main screen with client creation
//            // Include the button to navigate to the client list screen
//            MainScreen(
//                onCreateClient = { client ->
//                    // Add the client to the list
//                    clientsList.add(client)
//                },
//                navigateToClientList = {
//                    navController.navigate("clientList")
//                }
//            )
//        }
//        composable("clientList") {
//            // Client list screen
//            ClientListScreen(
//                clients = clientsList,
//                onClientClicked = { client ->
//                    // Handle client click, e.g., navigate to client details screen
//                    // navController.navigate("clientDetails/${client.id}")
//                }
//            )
//        }
//        // Add more composable blocks for other screens as needed
//    }
//}
//
//@Composable
//fun NavHost(navController: Any, startDestination: String, content: () -> Unit) {
//
//}
