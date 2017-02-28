package marcos.movieapp.searchMovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SearchView;

import com.google.common.base.Strings;

import java.util.List;

import marcos.movieapp.Injection;
import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.utils.FragmentUtils;

import static marcos.movieapp.home.MoviesFragment.SEARCH_MOVIE_RESULT;

public class SearchMovieActivity extends AppCompatActivity {

    private static final String TAG = SearchMovieActivity.class.getName();
    SearchView searchView;
    SearchMoviePresenter searchMoviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        SearchMovieFragment moviesFragment = (SearchMovieFragment)
            getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (moviesFragment == null) {
            moviesFragment = SearchMovieFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                getSupportFragmentManager(), moviesFragment, R.id.frame_container);
        }

        searchMoviePresenter = new SearchMoviePresenter(Injection.provideMovieRepository(this),
            moviesFragment, Injection.provideSchedulerProvider());

        searchView = (SearchView) findViewById(R.id.search_movie);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Strings.isNullOrEmpty(query)) {
                    return false;
                } else {
                    searchMoviePresenter.searchMovie(query);
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
