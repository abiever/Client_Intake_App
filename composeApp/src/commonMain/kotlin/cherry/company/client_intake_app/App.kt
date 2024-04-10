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

        var firstName by remember { mutableStateOf(("")) }
        TextField(
            value = firstName,
            label = { Text("Enter First Name:") },
            singleLine = true,
            onValueChange = { firstName = it },
            isError = firstName.isNotEmpty() && !isValidName(firstName),
            supportingText = {
                if (firstName.isNotEmpty() && !isValidName(firstName)) {
                    Text("Please enter a valid first name")
                }
            },
            modifier = Modifier.padding(20.dp)
        )

        var lastName by remember { mutableStateOf(("")) }
        TextField(
            value = lastName,
            label = { Text("Enter Last Name:") },
            singleLine = true,
            onValueChange = { lastName = it },
            isError = lastName.isNotEmpty() && !isValidName(lastName),
            supportingText = {
                if (lastName.isNotEmpty() && !isValidName(lastName)) {
                    Text("Please enter a valid last name")
                }
            },
            modifier = Modifier.padding(20.dp)
        )

        var birthDate by remember { mutableStateOf(("")) }
        TextField(
            value = birthDate,
            label = { Text("Date of Birth") },
            placeholder = { Text("MM/DD/YYYY")},
            singleLine = true,
            onValueChange = { birthDate = it },
            isError = birthDate.isNotEmpty() && !isValidBirthDate(birthDate),
            supportingText = {
                if (birthDate.isNotEmpty() && !isValidBirthDate(birthDate)) {
                    Text("Please enter a valid birth date")
                }
            },
            modifier = Modifier.padding(20.dp)
        )

        //Use this button to "save" the name to a field on the app for the time being
        //val isClicked = remember { mutableStateOf(false) }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                //isValidClientInfo() is used here to validate all client info at once
                //prevents button click if data does not pass validation
                if (isValidClientInfo(firstName, lastName, birthDate)) {
                    val client = Client(firstName, lastName, birthDate)
                    clientsList.add(client)
                    //The below 'resets' the text fields with empty strings
                    firstName = ""
                    lastName = ""
                    birthDate = ""
                }
            }) {
            Text("Create Client")
        }

        //TODO: Create a way to save this info & health issues to each instance of a Client()
        Text("How much did pain interfere with your enjoyment of life?")
        val radioOptions = listOf("Not at all", "A little bit", "Somewhat", "Quite a bit", "Very much")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
        Row {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(vertical = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 8.dp) // Adjust padding as needed
                    )
                }
            }
        }

        //TODO: Add modifier to text to be "heading" size and edit options for health issues options
        Text("Any general health issues?")
        CheckableRow()

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
        Text(text = client.getFirstName())
    }

    // Display additional content when the button is clicked
    if (expanded.value) {
        Text(text = client.getFirstName() + " " + client.getLastName() + "'s birthday is " + client.getBirthDate())
    }
}

fun isValidName(name: String): Boolean {
    return name.matches(Regex("^[A-Za-z'-]{2,20}\$"))
}

fun isValidBirthDate(birthDate: String): Boolean {
    //the below regular expression may not be ideal, but it appears to work and that's what matters for now
    val birthDateRegex = """^(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/(19|20)[0-9][0-9]"""
    return birthDate.matches(Regex(birthDateRegex))
}

//use this to validate all inputted client info at once when doing the final button click needed to "create" a new client
fun isValidClientInfo(firstName: String, lastName: String, birthDate: String): Boolean {
    //Add validation function as needed below
    return  isValidName(firstName) &&
            isValidName(lastName) &&
            isValidBirthDate(birthDate)
}

@Composable
fun CheckableRow() {
    MaterialTheme {
        val options = listOf("Option 1", "Option 2", "Option 3") // Add more options as needed
        val checkedState = remember { mutableStateListOf<Boolean>().apply { repeat(options.size) { add(false) } } }

        Column {
            options.forEachIndexed { index, option ->
                Row(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { checkedState[index] = !checkedState[index] })
                ) {
                    Text(option, Modifier.weight(1f))
                    Checkbox(checked = checkedState[index], onCheckedChange = { checkedState[index] = it })
                }
            }
        }
    }
}


internal expect fun openUrl(url: String?)