package by.overpass.treemapchart.sample.android

import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ComposeViewComparisonTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSquarifiedViewMatchesCompose() {
        lateinit var screen: ComparisonScreen
        composeRule.runOnUiThread {
            screen = composeRule.activity.showComparisonScreen(SquarifiedMeasurer(), SquarifiedMeasurer())
        }
        composeRule.waitUntil { screen.composeBounds.size == 6 }
        composeRule.waitForIdle()

        val viewBounds = composeRule.runOnUiThread { screen.viewBounds() }
        assertTrue(screen.saveScreenshot("squarified").exists())
        assertEquals(screen.composeBounds.toSortedMap(), viewBounds.toSortedMap())
    }

    @Test
    fun testSliceAndDiceViewMatchesCompose() {
        lateinit var screen: ComparisonScreen
        composeRule.runOnUiThread {
            screen = composeRule.activity.showComparisonScreen(SliceAndDiceMeasurer, SliceAndDiceMeasurer)
        }
        composeRule.waitUntil { screen.composeBounds.size == 6 }
        composeRule.waitForIdle()

        val viewBounds = composeRule.runOnUiThread { screen.viewBounds() }
        assertTrue(screen.saveScreenshot("slice_and_dice").exists())
        assertEquals(screen.composeBounds.toSortedMap(), viewBounds.toSortedMap())
    }
}
