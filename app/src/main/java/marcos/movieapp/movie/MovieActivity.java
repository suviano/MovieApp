package marcos.movieapp.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import marcos.movieapp.Injection;
import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovie;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.searchMovie.SearchMovieFragment.MOVIE_OVERVIEW_BUNDLE;

public class MovieActivity extends AppCompatActivity implements ContractMovie.View {

    private ContractMovie.Presenter presenter;
    private CoordinatorLayout movieCoordinator;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapsingToolbar;

    private ResMovie resMovie;

    private ImageView imgPoster;

    private TextView movieTitle;
    private TextView movieSubtitle;
    private TextView movieDescription;
    private TextView movieDirector;
    private TextView movieWriters;
    private TextView movieActors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieCoordinator = (CoordinatorLayout) findViewById(R.id.movie_coordinator_layout);
        appBar = (AppBarLayout) findViewById(R.id.movie_app_bar);
        toolbar = (Toolbar) findViewById(R.id.movie_toolbar);
        collapsingToolbar =
            (CollapsingToolbarLayout) findViewById(R.id.movie_collapsing_toolbar_layout);

        imgPoster = (ImageView) findViewById(R.id.movie_poster_img);

        movieTitle = (TextView) findViewById(R.id.movie_title_txt);
        movieSubtitle = (TextView) findViewById(R.id.movie_subtitle_txt);
        movieDescription = (TextView) findViewById(R.id.movie_description_txt);
        movieDirector = (TextView) findViewById(R.id.movie_director_txt);
        movieWriters = (TextView) findViewById(R.id.movie_writers_txt);
        movieActors = (TextView) findViewById(R.id.movie_actors_txt);

        presenter = new MoviePresenter(Injection.provideMovieRepository(getApplicationContext()),
            this, Injection.provideSchedulerProvider(), getMovieFromIntent());
        presenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void setPresenter(@NonNull ContractMovie.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void displaySearchResult(ResMovie resMovie) {
        this.resMovie = resMovie;
        Picasso.with(getApplicationContext()).load(resMovie.getPoster()).into(imgPoster);
        configureMovieTitle(resMovie.getTitle());
        movieTitle.setText(String.format("%s (%s)", resMovie.getTitle(), resMovie.getTyar()));
        movieSubtitle.setText(String.format("%s | %s | %s", resMovie.getRuntime(),
            resMovie.getGenre(), resMovie.getReleased()));
        movieDescription.setText(resMovie.getPlot());
        movieDirector.setText(resMovie.getDirector());
        movieWriters.setText(resMovie.getDirector());
        movieActors.setText(resMovie.getActors());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_movie_menu_item:
                presenter.saveMovie(resMovie);
                return true;
            case R.id.delete_movie_menu_item:
                Log.wtf(MoviePresenter.TAG, "delete");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public MovieOverview getMovieFromIntent() {
        return getIntent().getParcelableExtra(MOVIE_OVERVIEW_BUNDLE);
    }

    @Override
    public void configureMovieTitle(@NonNull String title) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (collapsingToolbar != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                collapsingToolbar.setTitle(title);
            } else {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }
}
