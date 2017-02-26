package marcos.movieapp.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.SearchView;

import com.google.common.base.Strings;

import marcos.movieapp.Injection;
import marcos.movieapp.R;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.utils.FragmentUtils;

public class MovieActivity extends AppCompatActivity {

    private final String TAG = MovieActivity.class.getName();

    SearchView searchView;

    private MoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MovieFragment movieFragment = (MovieFragment)
            getSupportFragmentManager().findFragmentById(R.id.movie_result);
        if (movieFragment == null) {
            movieFragment = MovieFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                getSupportFragmentManager(), movieFragment, R.id.movie_result);
        }

        moviePresenter = new MoviePresenter(Injection.provideMovieRepository(this),
            movieFragment, Injection.provideSchedulerProvider());

        searchView = (SearchView) findViewById(R.id.search_movie);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Strings.isNullOrEmpty(query)) {
                    return false;
                } else {
                    moviePresenter.searchMovie(query);
                    return true;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
