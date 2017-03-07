package marcos.movieapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.movie.MovieActivity;
import marcos.movieapp.searchMovie.SearchMovieActivity;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.home.HomePresenter.TAG;
import static marcos.movieapp.movie.MovieActivity.MOVIE_TITLE;

public class HomeFragment extends Fragment implements Contract.View, HomeSavedClickListener {

    public static final String SEARCH_MOVIE_RESULT = "SEARCH_MOVIE_RESULT";

    private Contract.Presenter presenter;
    private HomeListAdapter adapter;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_movies_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeListAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.unSubscribe();
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

    @Override
    public void displayFavorites(List<ResMovie> resMovies) {
        adapter.updateHomeResMovies(resMovies);
    }

    @Override
    public void seeDetails(@NonNull View view, @NonNull String movieTitle) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra(MOVIE_TITLE, movieTitle);
        startActivity(intent);
    }
}
