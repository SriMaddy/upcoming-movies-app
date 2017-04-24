package com.sridhar.upcomingmovies;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sridhar.upcomingmovies.fragment.MoviesListFragment;

public class MainActivity extends AppCompatActivity {

    // UI references
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();
        setUpToolbar();
        addMoviesListFragment();
    }

    private void findViewsById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setUpToolbar() {
        toolbar.setTitle("Upcoming MovieResult");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    private void addMoviesListFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MoviesListFragment());
        fragmentTransaction.commit();
    }
}
