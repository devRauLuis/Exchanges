import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val icon: ImageVector,
    val displayText: String = "",
) {
    Home(
        icon = Icons.Filled.Home,
        displayText = "Inicio"
    ),
    Registry(
        icon = Icons.Filled.Person,
        displayText = "Registro Criptomoneda",
    );

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Registry.name -> Registry
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}