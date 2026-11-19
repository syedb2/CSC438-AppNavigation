package com.example.appnavigation

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import java.io.File

class GalleryTest {
    @get:Rule val rule = createAndroidComposeRule<MainActivity>()

    private fun capture(name: String) {
        rule.waitForIdle()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val file = File(context.getExternalFilesDir(null), "$name.png")
        file.outputStream().use {
            rule.onRoot().captureToImage().asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        android.os.ParcelFileDescriptor.AutoCloseInputStream(
            InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(
                "cp ${file.absolutePath} /sdcard/Download/$name.png"
            )
        ).use { it.readBytes() }
    }

    @Test fun listAndNavigationWork() {
        rule.onNodeWithText("Mercury").assertIsDisplayed()
        capture("01-home")
        for (name in listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
            "Uranus", "Neptune", "Earth detail", "Jupiter detail")) {
            rule.onNodeWithTag("gallery").performScrollToNode(hasText(name))
            rule.waitForIdle()
            capture("item-${name.replace(" ", "-")}")
            rule.onNodeWithText(name).assertIsDisplayed()
        }
        capture("02-scrolled-home")
        rule.onNodeWithText("Contact").performClick()
        rule.onNodeWithText("Syed Wajid Bukhari").assertIsDisplayed()
        rule.onNodeWithText("github.com/syedb2").assertIsDisplayed()
        rule.onNodeWithContentDescription("Computer science business card logo").assertIsDisplayed()
        capture("03-contact")
        rule.onNodeWithContentDescription("Back to home").performClick()
        rule.onNodeWithText("Jupiter detail").assertIsDisplayed()
        rule.onNodeWithContentDescription("Back to home").assertDoesNotExist()
        rule.onNodeWithText("Contact").performClick()
        rule.onNodeWithText("Syed Wajid Bukhari").assertIsDisplayed()
        rule.activityRule.scenario.recreate()
        rule.onNodeWithText("Syed Wajid Bukhari").assertIsDisplayed()
        rule.onNodeWithContentDescription("Back to home").performClick()
        rule.onNodeWithText("Solar System").assertIsDisplayed()
    }
}
