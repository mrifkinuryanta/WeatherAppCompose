package com.mrndevs.weatherapp


import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import com.mrndevs.weatherapp.ui.screen.weather.WeatherScreen
import com.mrndevs.weatherapp.ui.screen.weather.component.WeatherStatus
import com.mrndevs.weatherapp.ui.theme.WeatherAppTheme
import com.mrndevs.weatherapp.ui.theme.onBackgroundLight
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode


@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], qualifiers = RobolectricDeviceQualifiers.Pixel4)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testWeatherApp() {
        composeTestRule.setContent {
            WeatherAppTheme {
                WeatherStatus(onBackgroundLight)
            }
        }

        println(composeTestRule.onRoot().printToString())
        composeTestRule.onRoot().captureRoboImage()
    }
}