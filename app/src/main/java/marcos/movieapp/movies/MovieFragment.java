package marcos.movieapp.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.ResMovies;

import static com.google.common.base.Preconditions.checkNotNull;

public class MovieFragment extends Fragment implements Contract.View {

    final String TAG = MovieFragment.class.getName();

    private Contract.Presenter presenter;

    public MovieFragment() {
    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
        LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(@NonNull Contract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
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

    @Override
    public void showResult(ResMovies resMovies) {
        Log.d(TAG, "<:=:>" + resMovies.getTotalResults());
    }
}
