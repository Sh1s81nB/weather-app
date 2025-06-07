package org.weather_app.project.features.language.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import org.weather_app.project.features.language.LanguageScreenRoute

const val languageRoute = "language_route"
const val paramShouldNavigateBack = "shouldNavigateBack"

fun NavController.navigateToLanguage(
    navOptions: NavOptions? = null,
    shouldNavigateBack: Boolean = false
) {
    this.navigate("$languageRoute/$shouldNavigateBack", navOptions)
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.languageScreen(
    navigateUp: () -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = "$languageRoute/{$paramShouldNavigateBack}",
        arguments = listOf(
            navArgument(paramShouldNavigateBack) { type = NavType.BoolType }
        )
    ) {
        LanguageScreenRoute(
            navigateUp = navigateUp,
            navigateBack = navigateBack,
            viewModel = koinViewModel(
                parameters = {
                    parametersOf(
                        SavedStateHandle.createHandle(
                            null,
                            it.arguments
                        )
                    )
                }
            )
        )
    }
}