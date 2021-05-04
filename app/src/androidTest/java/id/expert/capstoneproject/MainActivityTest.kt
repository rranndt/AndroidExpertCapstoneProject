package id.expert.capstoneproject

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private fun delay() {
        Thread.sleep(4000L)
    }

    @Test
    fun showListMovies() {
        delay()
        onView(allOf(withId(R.id.iv_poster), isDisplayed()))
        onView(allOf(withId(R.id.tv_title), isDisplayed()))
        onView(allOf(withId(R.id.rating_bar), isDisplayed()))
        onView(allOf(withId(R.id.tv_rating), isDisplayed()))
        onView(withId(R.id.scroll_view)).perform(swipeUp())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                19,
                click()
            )
        )
    }

    @Test
    fun showDetailAndFavoriteMovies() {
        delay()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.iv_poster), isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_title), isDisplayed()))
        onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_count)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.rating_bar), isDisplayed()))
        onView(allOf(withId(R.id.tv_rating), isDisplayed()))
        onView(allOf(withId(R.id.tv_overview), isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withId(R.id.btn_share)).perform(click())
        pressBack()

        delay()
        onView(withId(R.id.rv_similar_movies)).perform(swipeLeft())
        onView(withId(R.id.rv_similar_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_similar_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                19,
                click()
            )
        )
    }

}