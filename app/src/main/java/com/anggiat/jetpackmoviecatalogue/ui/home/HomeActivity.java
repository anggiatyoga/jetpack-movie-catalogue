package com.anggiat.jetpackmoviecatalogue.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.anggiat.jetpackmoviecatalogue.R;
import com.anggiat.jetpackmoviecatalogue.ui.favorite.FavoriteFragment;
import com.anggiat.jetpackmoviecatalogue.ui.movies.MovieFragment;
import com.anggiat.jetpackmoviecatalogue.ui.tvshow.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getFragmentPage(new MovieFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.movie:
                    fragment = new MovieFragment();
                    break;
                case R.id.tv_show:
                    fragment = new TvShowFragment();
                    break;
                case R.id.favorite:
                    fragment = new FavoriteFragment();
                    break;
            }
            return getFragmentPage(fragment);
        });
    }

    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
