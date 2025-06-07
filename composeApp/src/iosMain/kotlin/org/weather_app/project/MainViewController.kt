package org.weather_app.project

import androidx.compose.ui.window.ComposeUIViewController
import org.weather_app.project.commonconfigs.Context
import platform.UIKit.UIViewController


fun MainViewController(): UIViewController = ComposeUIViewController {
   App(Context())
}