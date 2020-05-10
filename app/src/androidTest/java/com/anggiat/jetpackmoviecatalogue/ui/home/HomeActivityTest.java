package com.anggiat.jetpackmoviecatalogue.ui.home;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.data.MovieEntity;
import com.anggiat.jetpackmoviecatalogue.data.TvShowEntity;
import com.anggiat.jetpackmoviecatalogue.utils.DataDummy;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {

    private ArrayList<MovieEntity> dummyMovie = DataDummy.generateDataMovies();
    private ArrayList<TvShowEntity> dummyTvShow = DataDummy.generateDataTvShows();

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition(dummyMovie.size()));
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyMovie.get(0).getTitle())));
        onView(withId(R.id.tv_item_genre_one)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_genre_one)).check(matches(withText(dummyMovie.get(0).getGenreOne())));
        onView(withId(R.id.tv_item_genre_two)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_genre_two)).check(matches(withText(dummyMovie.get(0).getGenreTwo())));
        onView(withId(R.id.tv_vote_average_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_vote_average_detail)).check(matches(withText(dummyMovie.get(0).getVoteAverage())));
        onView(withId(R.id.tv_release_date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date_detail)).check(matches(withText(dummyMovie.get(0).getReleaseDate())));
        onView(withId(R.id.tv_runtime_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime_detail)).check(matches(withText(dummyMovie.get(0).getRuntime())));
        onView(withId(R.id.tv_language_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language_detail)).check(matches(withText(dummyMovie.get(0).getSpokenLanguages())));
        onView(withId(R.id.tv_overview_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview_detail)).check(matches(withText(dummyMovie.get(0).getOverview())));
        onView(withId(R.id.fab_play_detail)).perform(click());
    }

    @Test
    public void loadTvShow() {
        onView(withText("Tv Show")).perform(click());
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition(dummyTvShow.size()));
    }

    @Test
    public void loadDetailTvShow() {
        onView(withText("Tv Show")).perform(click());
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyTvShow.get(0).getName())));
        onView(withId(R.id.tv_item_genre_one)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_genre_one)).check(matches(withText(dummyTvShow.get(0).getGenreOne())));
        onView(withId(R.id.tv_item_genre_two)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_item_genre_two)).check(matches(withText(dummyTvShow.get(0).getGenreTwo())));
        onView(withId(R.id.tv_vote_average_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_vote_average_detail)).check(matches(withText(dummyTvShow.get(0).getVoteAverage())));
        onView(withId(R.id.tv_release_date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release_date_detail)).check(matches(withText(dummyTvShow.get(0).getFirstAirDate())));
        onView(withId(R.id.tv_runtime_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_runtime_detail)).check(matches(withText(dummyTvShow.get(0).getNumberOfSeasons())));
        onView(withId(R.id.tv_language_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_language_detail)).check(matches(withText(dummyTvShow.get(0).getNumberOfEpisodes())));
        onView(withId(R.id.tv_overview_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview_detail)).check(matches(withText(dummyTvShow.get(0).getOverview())));
        onView(withId(R.id.fab_play_detail)).perform(click());
    }

}