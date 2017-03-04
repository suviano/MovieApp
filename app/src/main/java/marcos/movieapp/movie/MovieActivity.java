package marcos.movieapp.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieCoordinator = (CoordinatorLayout) findViewById(R.id.movie_coordinator_layout);
        appBar = (AppBarLayout) findViewById(R.id.movie_app_bar);
        toolbar = (Toolbar) findViewById(R.id.movie_toolbar);
        collapsingToolbar =
            (CollapsingToolbarLayout) findViewById(R.id.movie_collapsing_toolbar_layout);

        presenter = new MoviePresenter(Injection.provideMovieRepository(getApplicationContext()),
            this, Injection.provideSchedulerProvider(), getMovieFromIntent());
        presenter.subscribe();

        FloatingActionButton fab =
            (FloatingActionButton) findViewById(R.id.add_movie_favorites_fab);
        fab.setOnClickListener(view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        );
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
        // TODO
        configureMovieTitle(resMovie.getTitle());
    }

    @Override
    public MovieOverview getMovieFromIntent() {
        return getIntent().getParcelableExtra(MOVIE_OVERVIEW_BUNDLE);
    }

    @Override
    public void configureMovieTitle(@NonNull String title) {
        setSupportActionBar(toolbar);
        if (appBar != null) {
            if (appBar.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.LayoutParams layoutParams =
                    (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
                layoutParams.height = getResources().getDisplayMetrics().widthPixels;
            }
        }
        if (getSupportActionBar() != null) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (collapsingToolbar != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                collapsingToolbar.setTitle(title);
            } else {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

}
