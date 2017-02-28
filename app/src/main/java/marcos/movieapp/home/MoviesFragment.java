package marcos.movieapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.searchMovie.SearchMovieActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class MoviesFragment extends Fragment implements Contract.View {

    public static final String SEARCH_MOVIE_RESULT = "SEARCH_MOVIE_RESULT";
    public static final String TAG = MoviesFragment.class.getName();

    private Contract.Presenter presenter;

    public MoviesFragment() {
    }

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.unSubscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.unSubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void setPresenter(@NonNull Contract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void displaySearchResult(ResMovies resMovies) {
        if (resMovies != null) {
            if (resMovies.getResponse().equalsIgnoreCase("true")) {
                Intent intent = new Intent(getActivity(), SearchMovieActivity.class);
                intent.putParcelableArrayListExtra(SEARCH_MOVIE_RESULT,
                    (ArrayList<MovieOverview>) resMovies.getMovies());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Log.wtf(TAG, resMovies.getError());
            }
        }
    }
}
