package marcos.movieapp.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import marcos.movieapp.Injection;
import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovie;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.searchMovie.SearchMovieFragment.MOVIE_OVERVIEW_BUNDLE;

public class MovieActivity extends AppCompatActivity implements ContractMovie.View {

    private ContractMovie.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MoviePresenter(Injection.provideMovieRepository(getApplicationContext()),
            this, Injection.provideSchedulerProvider(), getMovieFromIntent());
        presenter.subscribe();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        );*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_movie_menu_item:
                break;
            case R.id.save_movie_menu_item:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(@NonNull ContractMovie.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void displaySearchResult(ResMovie resMovie) {
        Log.wtf("LALA", resMovie.getTitle());
    }

    @Override
    public MovieOverview getMovieFromIntent() {
        return getIntent().getParcelableExtra(MOVIE_OVERVIEW_BUNDLE);
    }
}
