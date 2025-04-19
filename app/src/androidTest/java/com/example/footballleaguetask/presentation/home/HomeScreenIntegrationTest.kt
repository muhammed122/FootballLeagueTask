package com.example.footballleaguetask.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.footballleaguetask.MainActivity
import com.example.footballleaguetask.di.AppModule
import com.example.footballleaguetask.di.BindAppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class, BindAppModule::class)
class HomeScreenIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun homeScreen_displaysAreaAndNavigatesToCompetitionDetails() {
        // Wait for "Europe" to appear
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule
                .onAllNodesWithText("Europe (EUR)")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // Check if the area name is displayed
        composeTestRule.onNodeWithText("Europe (EUR)").assertIsDisplayed()

        // Click to expand the area
        composeTestRule.onNodeWithText("Europe (EUR)").performClick()

        // Wait for competition to appear
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule
                .onAllNodesWithText("UEFA Champions League")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // Click on competition
        composeTestRule.onNodeWithText("UEFA Champions League").performClick()

        // Verify navigation title
        composeTestRule.onNodeWithText("Competition Details").assertIsDisplayed()
    }
}
