package marcos.movieapp.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.google.common.base.Strings;

import marcos.movieapp.Injection;
import marcos.movieapp.R;
import marcos.movieapp.utils.FragmentUtils;


public class MoviesActivity extends AppCompatActivity {
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MoviesFragment moviesFragment = (MoviesFragment)
            getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (moviesFragment == null) {
            moviesFragment = MoviesFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                getSupportFragmentManager(), moviesFragment, R.id.frame_container);
        }

        MoviesPresenter moviesPresenter = new MoviesPresenter(Injection.provideMovieRepository(this),
            moviesFragment, Injection.provideSchedulerProvider());

        searchView = (SearchView) findViewById(R.id.search_movie);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!Strings.isNullOrEmpty(query)) {
                    moviesPresenter.searchMovie(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
