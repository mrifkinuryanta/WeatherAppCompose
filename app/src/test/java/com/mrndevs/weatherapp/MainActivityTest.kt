package com.mrndevs.weatherapp


import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.captureRoboImage
import com.mrndevs.weatherapp.ui.screen.weather.WeatherScreen
import com.mrndevs.weatherapp.ui.theme.WeatherAppTheme
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

    @OptIn(ExperimentalRoborazziApi::class)
    @Test
    fun testWeatherApp() {
        composeTestRule.setContent {
            WeatherAppTheme {
                WeatherScreen()
            }
        }

        println(composeTestRule.onRoot().printToString())
        composeTestRule.onRoot().captureRoboImage(
            roborazziOptions = RoborazziOptions(
                recordOptions =
                RoborazziOptions.RecordOptions(resizeScale = 0.1)
            )
        )
    }
}