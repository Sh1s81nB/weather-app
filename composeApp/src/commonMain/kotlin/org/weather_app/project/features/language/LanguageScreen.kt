package org.weather_app.project.features.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.weather_app.project.features.language.domain.Language
import weather_app.composeapp.generated.resources.Res
import weather_app.composeapp.generated.resources.apply
import weather_app.composeapp.generated.resources.change_language
import weather_app.composeapp.generated.resources.choose_language

@Composable
fun LanguageScreenRoute(
    viewModel: LanguageViewModel,
    navigateUp: () -> Unit,
    navigateBack: () -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LanguageScreen(
        uiState = uiState,
        onLanguageSelected = viewModel::updateSelectedLanguage,
        onUiEvent = viewModel::uiEvent,
        navigateBack = navigateBack,
        navigateUp = {
            viewModel.setLanguageSelectionVisited()
            navigateUp()
        },
        shouldNavigateBack = viewModel.shouldNavigateBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(
    uiState: LanguageUiState,
    onLanguageSelected: (Language) -> Unit,
    onUiEvent: (LanguageUiEvent) -> Unit,
    navigateUp: () -> Unit,
    navigateBack: () -> Unit,
    shouldNavigateBack: Boolean,
){
    when(uiState){
        is LanguageUiState.LanguageList -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(Res.string.change_language)) }
                    )
                },
                bottomBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ){
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onUiEvent(LanguageUiEvent.onConfirmClicked)
                                if (shouldNavigateBack) {
                                    navigateBack()
                                } else {
                                    navigateUp()
                                }
                            },
                        ) {
                            Text(stringResource(Res.string.apply))
                        }
                    }
                }
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .padding(it)
                        .padding(16.dp)
                ){
                    Column {
                        LanguageOptionsCard(
                            languages = uiState.languages,
                            selectedLanguage = uiState.selectedLanguage,
                            onLanguageSelected = onLanguageSelected
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LanguageOptionsCard(
    languages: List<Language>,
    selectedLanguage: Language?,
    onLanguageSelected: (Language) -> Unit
){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.choose_language),
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = MaterialTheme.typography.headlineMedium.fontWeight
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn{
            items(languages){ language ->
                LanguageItem(
                    language = language,
                    selected = selectedLanguage == language,
                    onLanguageSelected = onLanguageSelected
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (language != languages.last()) {
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }
        }
    }
}

@Composable
fun LanguageItem(
    language: Language,
    selected: Boolean,
    onLanguageSelected: (Language) -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable(
                interactionSource = remember{ MutableInteractionSource()  },
                indication = null,
                onClick = { onLanguageSelected(language) }
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected,
                onClick = { onLanguageSelected(language) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = language.details.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = language.details.languageName,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}