import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import cherry.company.client_intake_app.App

fun main() = application {
    Window(
        //This is where the app could be "initialized" by the eventual end-user to be in-line with
        //their company/business/practice
        title = "Client_Intake_App",
        state = rememberWindowState(width = 1200.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(850, 600)
        App()
    }
}