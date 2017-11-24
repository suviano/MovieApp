package marcos.movieapp.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.crashlytics.android.Crashlytics;
import com.google.common.base.Strings;

import io.fabric.sdk.android.Fabric;
import marcos.movieapp.Injection;
import marcos.movieapp.R;
import marcos.movieapp.utils.FragmentUtils;


public class HomeActivity extends AppCompatActivity {
    SearchView searchView;
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomeFragment homeFragment = (HomeFragment)
            getSupportFragmentManager().findFragmentById(R.id.frame_container_home);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                getSupportFragmentManager(), homeFragment, R.id.frame_container_home, false);
        }

        homePresenter = new HomePresenter(Injection.provideMovieRepository(this),
            homeFragment, Injection.provideSchedulerProvider());

        searchView = findViewById(R.id.search_movie);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!Strings.isNullOrEmpty(query)) {
                    homePresenter.searchMovie(query);
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
