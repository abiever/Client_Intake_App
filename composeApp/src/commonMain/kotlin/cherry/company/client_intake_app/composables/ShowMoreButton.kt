package cherry.company.client_intake_app.composables

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cherry.company.client_intake_app.Client

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
        Text(text = client.getFirstName() + " " +
                client.getLastName() + "'s birthday is " +
                client.getBirthDate() + " and their initial pain was " +
                client.getInitialPain()
        )
    }
}
