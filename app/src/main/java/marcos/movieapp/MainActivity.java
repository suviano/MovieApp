package marcos.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import javax.inject.Inject;

import marcos.movieapp.layers.BasePresenter;
import marcos.movieapp.layers.BaseView;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import marcos.movieapp.root.App;

public class MainActivity extends AppCompatActivity implements BaseView {

    @Inject
    BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnSubscribe();
    }

    // TODO pass data to view
    @Override
    public void updateData(OMDBResponse omdbResponse) {
        Log.d("MOVIES", omdbResponse.getMovieOnList().toString());
    }

}
