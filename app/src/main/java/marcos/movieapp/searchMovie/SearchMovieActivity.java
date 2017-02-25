package marcos.movieapp.searchMovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import javax.inject.Inject;

import marcos.movieapp.R;
import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import marcos.movieapp.MovieApplication;

public class SearchMovieActivity extends AppCompatActivity implements BaseView {

    @Inject
    BasePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((MovieApplication) getApplication()).getComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.rxUnSubscribe();
    }

    // TODO pass data to view
    @Override
    public void updateData(OMDBResponse omdbResponse) {
        Log.d("MOVIES", omdbResponse.getMovieOnList().toString());
    }

}
