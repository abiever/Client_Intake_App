package cherry.company.client_intake_app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import client_intake_app.composeapp.generated.resources.*
import cherry.company.client_intake_app.theme.AppTheme
import cherry.company.client_intake_app.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.vectorResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
internal fun App() = AppTheme {

    val clientsList = remember { mutableStateListOf<Client>() } // Use mutableStateListOf instead of mutableListOf
    // State variable to hold the list size
    var listSize by remember { mutableStateOf(0) }

    //Insert row here?
    //TODO: Make 2 columns side by side, where one column displays the input fields, and the other displays already created clients
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

        //Trying to create some text fields
        var firstName by remember { mutableStateOf(("")) }
        TextField(
            value = firstName,
            label = { Text("First Name") },
            singleLine = true,
            onValueChange = { firstName = it },
            modifier = Modifier
                .padding(20.dp)
//                .clickable() //TODO: add 'clickable' to remove text
            // https://stackoverflow.com/questions/68482228/how-to-clear-textfield-value-in-jetpack-compose
        )

        var lastName by remember { mutableStateOf(("")) }
        TextField(
            value = lastName,
            label = { Text("Last Name") },
            singleLine = true,
            onValueChange = { lastName = it },
            modifier = Modifier.padding(20.dp)
        )

        var birthDate by remember { mutableStateOf(("")) }
        TextField(
            value = birthDate,
            label = { Text("Date of Birth") },
            placeholder = { Text("MM/DD/YYYY")},
            singleLine = true,
            onValueChange = { birthDate = it },
            modifier = Modifier.padding(20.dp)
        )

        //Use this button to "save" the name to a field on the app for the time being
        val isClicked = remember { mutableStateOf(false) }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val client = Client(firstName, lastName, birthDate)
                clientsList.add(client)
                //The below 'resets' the text fields with empty strings
                //TODO: Disallow the form from being submitted if input is ONLY ""
                firstName = ""
                lastName = ""
                birthDate = ""
            }) {
            Text("Create Client")
        }

        if (clientsList.isNotEmpty()) {
            for (client in clientsList) {
                // Use a composable function to create the button
                ShowMoreButton(client = client)
            }
        }


        //Todo: add dropdown and radio buttons
        //The data can be saved in the app or on the hosting machine itself for now
    }
}

@Composable
fun ShowMoreButton(client: Client) {
    // State to track whether the button is clicked
    val expanded = remember { mutableStateOf(false) }

    Button(
        onClick = { expanded.value = true }
    ) {
        Text("Show More")
    }

    // Display additional content when the button is clicked
    if (expanded.value) {
        Text(text = client.getBirthDate())
    }
}


internal expect fun openUrl(url: String?)