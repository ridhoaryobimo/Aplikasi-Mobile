/*
 * Copyright (C) 2018 Shehab Salah
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ridhoaryobimo.PAM.ResponsiMovie.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import ridhoaryobimo.PAM.ResponsiMovie.R;
import ridhoaryobimo.PAM.ResponsiMovie.data.Movie;
import ridhoaryobimo.PAM.ResponsiMovie.util.ActivityUtils;
import ridhoaryobimo.PAM.ResponsiMovie.util.Constants;

public class DetailsActivity extends AppCompatActivity {

    public static Intent getDetailsIntent(Context context, Movie movie) {
        return new Intent(context, DetailsActivity.class).putExtra(Constants.MOVIE_EXTRA, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set an exit transition
        getWindow().setAllowEnterTransitionOverlap(true);
        setContentView(R.layout.activity_details);
        if (getIntent().hasExtra(Constants.MOVIE_EXTRA)) {
            Movie movie = getIntent().getParcelableExtra(Constants.MOVIE_EXTRA);
            setTitle(movie.getTitle());
        }
        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            Bundle extras = getIntent().getExtras();
            args.putParcelable(Constants.MOVIE_EXTRA, getIntent().getParcelableExtra(Constants.MOVIE_EXTRA));
            if (getIntent().hasExtra(Constants.KEY_CONNECTION_IMAGE) && extras != null)
                args.putString(Constants.KEY_CONNECTION_IMAGE, extras.getString(Constants.KEY_CONNECTION_IMAGE));
            DetailsFragment detailsFragment = DetailsFragment.newInstance();
            detailsFragment.setArguments(args);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), detailsFragment, R.id.details_container);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
