package marcos.movieapp.searchMovie;

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
import android.widget.Toast;

import java.util.List;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.movie.MovieActivity;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.home.HomeFragment.SEARCH_MOVIE_RESULT;
import static marcos.movieapp.movie.MovieActivity.MOVIE_TITLE;

public class SearchMovieFragment extends Fragment
    implements ContractSearchMovie.View, SearchResultClickListener {

    private ContractSearchMovie.Presenter presenter;
    private SearchMovieListAdapter adapter;
    private List<MovieOverview> movieOverviews;

    public SearchMovieFragment() {
    }

    public static SearchMovieFragment newInstance() {
        return new SearchMovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        movieOverviews = getMovieFromIntent();

        View root = inflater.inflate(R.layout.fragment_search_movie, container, false);

        RecyclerView recyclerView =
            root.findViewById(R.id.movie_overview_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new SearchMovieListAdapter(movieOverviews, getContext(), this);

        recyclerView.setAdapter(adapter);

        return root;
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
    public void setPresenter(@NonNull ContractSearchMovie.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void displaySearchResult(ResMovies resMovies) {
        this.movieOverviews = resMovies.getMovies();
        if (resMovies.getResponse().equalsIgnoreCase("true")) {
            adapter.updateMoviesOverview(resMovies.getMovies());
        } else {
            Toast.makeText(getActivity(), resMovies.getError(), Toast.LENGTH_LONG).show();
            Log.wtf(SearchMoviePresenter.TAG, resMovies.getError());
        }
    }

    public List<MovieOverview> getMovieFromIntent() {
        return getActivity().getIntent()
            .getParcelableArrayListExtra(SEARCH_MOVIE_RESULT);
    }

    @Override
    public void seeDetails(String movieTitle) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra(MOVIE_TITLE, movieTitle);
        startActivity(intent);
    }
}
