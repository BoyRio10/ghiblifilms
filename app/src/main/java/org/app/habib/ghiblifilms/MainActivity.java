package org.app.habib.ghiblifilms;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

import org.app.habib.ghiblifilms.view.util.Tag;
import org.app.habib.ghiblifilms.R;
import org.app.habib.ghiblifilms.di.App;
import org.app.habib.ghiblifilms.model.NavigationDrawerItem;
import org.app.habib.ghiblifilms.view.fragment.CharacterDetailsFragment;
import org.app.habib.ghiblifilms.view.fragment.CharacterListFragmentList;
import org.app.habib.ghiblifilms.view.fragment.FilmDetailsFragment;
import org.app.habib.ghiblifilms.view.fragment.FilmListFragmentList;
import org.app.habib.ghiblifilms.view.fragment.LocationDetailsFragment;
import org.app.habib.ghiblifilms.view.fragment.LocationListFragmentList;
import org.app.habib.ghiblifilms.view.fragment.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.FragmentDrawerListener {

    private AdView mAdView;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        ((App) getApplication()).getAppComponent().inject(this);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.cover_title));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragment navDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        navDrawerFragment.setDrawerListener(this);

        try {
            Glide.with(this).load(R.drawable.cover).centerCrop().into((ImageView)findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setScreenOrientation();

        if (getResources().getBoolean(R.bool.two_panes)) {
            insertDefaultDetailsFragment();
        }

        insertFilmListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about_us) {
            DialogFragment newFragment = new AboutUsFragment();
            newFragment.show(getSupportFragmentManager(), DialogFragment.class.getSimpleName());
            return true;
        }

        if (id == R.id.action_rate_app) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.app.habib.ghiblifilms"));
            startActivity(browserIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setScreenOrientation() {
        if (getResources().getBoolean(R.bool.two_panes)) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (position != NavigationDrawerItem.SELECTED) {
            switch (position) {
                case NavigationDrawerItem.FILMS:
                    if (getResources().getBoolean(R.bool.two_panes)) {
                        insertFilmDetailsFragment();
                    }
                    insertFilmListFragment();
                    break;
                case NavigationDrawerItem.CHARACTERS:
                    if (getResources().getBoolean(R.bool.two_panes)) {
                        insertCharacterDetailsFragment();
                    }
                    insertCharacterListFragment();
                    break;
                case NavigationDrawerItem.LOCATIONS:
                    if (getResources().getBoolean(R.bool.two_panes)) {
                        insertLocationDetailsFragment();
                    }
                    insertLocationsListFragment();
                    break;
            }
            NavigationDrawerItem.SELECTED = position;
        }
    }


    private void insertDefaultDetailsFragment() {
        FilmDetailsFragment fragment = new FilmDetailsFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.details_fragment, fragment, Tag.TAG_DETAILS_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    private void insertFilmDetailsFragment() {
        FilmDetailsFragment fragment = new FilmDetailsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, fragment, Tag.TAG_DETAILS_FRAGMENT)
                .commit();

    }

    private void insertCharacterDetailsFragment() {
        CharacterDetailsFragment fragment = new CharacterDetailsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, fragment, Tag.TAG_DETAILS_FRAGMENT)
                .commit();
    }

    private void insertLocationDetailsFragment() {
        LocationDetailsFragment fragment = new LocationDetailsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, fragment, Tag.TAG_DETAILS_FRAGMENT)
                .commit();
    }

    private void insertFilmListFragment() {
        FilmListFragmentList fragment = new FilmListFragmentList();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.list_fragment, fragment, Tag.TAG_LIST_FRAGMENT)
                .commit();
    }

    private void insertCharacterListFragment() {
        CharacterListFragmentList fragment = new CharacterListFragmentList();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_fragment, fragment, Tag.TAG_LIST_FRAGMENT).commit();
    }

    private void insertLocationsListFragment() {
        LocationListFragmentList fragment = new LocationListFragmentList();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_fragment, fragment, Tag.TAG_LIST_FRAGMENT).commit();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void adMobListener() {
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {

                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {

                Log.i("Ads", "onAdClosed");
            }
        });
    }
}
