package org.app.habib.ghiblifilms.view.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.app.habib.ghiblifilms.R;
import org.app.habib.ghiblifilms.model.Character;
import org.app.habib.ghiblifilms.model.Film;
import org.app.habib.ghiblifilms.model.Location;
import org.app.habib.ghiblifilms.view.fragment.CharacterDetailsFragment;
import org.app.habib.ghiblifilms.view.fragment.FilmDetailsFragment;
import org.app.habib.ghiblifilms.view.fragment.LocationDetailsFragment;
import org.app.habib.ghiblifilms.view.util.Tag;



public class DetailsActivity extends AppCompatActivity {
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setAnimationSlideToLeft();

        insertDetailsFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void insertDetailsFragment() {
        Fragment fragment = null;

        Film film = getIntent().getParcelableExtra("film");
        if (film != null) {
            this.title = film.getTitle();

            Bundle bundle = new Bundle();
            bundle.putParcelable("film", film);

            fragment = new FilmDetailsFragment();
            fragment.setArguments(bundle);
        }

        Character character = getIntent().getParcelableExtra("character");
        if (character != null) {
            this.title = character.getName();

            Bundle bundle = new Bundle();
            bundle.putParcelable("character", character);

            fragment = new CharacterDetailsFragment();
            fragment.setArguments(bundle);
        }

        Location location = getIntent().getParcelableExtra("location");
        if (location != null) {
            this.title = location.getName();

            Bundle bundle = new Bundle();
            bundle.putParcelable("location", location);

            fragment = new LocationDetailsFragment();
            fragment.setArguments(bundle);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.details_fragment, fragment, Tag.TAG_DETAILS_FRAGMENT).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setAnimationSlideToRight();
    }

    private void setAnimationSlideToLeft() {
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
    }

    private void setAnimationSlideToRight() {
        overridePendingTransition(R.anim.right_slide_out, R.anim.right_slide_in);
    }

}
