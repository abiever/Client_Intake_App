package cherry.company.client_intake_app.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckableRow(checkedState: MutableList<Boolean>) {
    MaterialTheme {
        val options = listOf("Option 1", "Option 2", "Option 3") // Add more options as needed
        //The below line of code was necessary to recompile
        checkedState.apply { repeat(options.size) { add(false) } }

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
