package marcos.movieapp.searchMovie;

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

import java.util.List;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovies;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.home.MoviesFragment.SEARCH_MOVIE_RESULT;

public class SearchMovieFragment extends Fragment implements ContractSearchMovie.View {

    final String TAG = SearchMovieFragment.class.getName();

    private ContractSearchMovie.Presenter presenter;
    private List<MovieOverview> movieOverviews;
    private SearchMovieListAdapter adapter;

    public SearchMovieFragment() {
    }

    public static SearchMovieFragment newInstance() {
        return new SearchMovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        this.movieOverviews = getMovieFromIntent();

        View root = inflater.inflate(R.layout.fragment_search_movie, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.movie_overview_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter = new SearchMovieListAdapter(movieOverviews, getContext());
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        if (resMovies.getResponse().equalsIgnoreCase("true")) {
            adapter.updateMoviesOverview(resMovies.getMovies());
        } else {
            Log.wtf(TAG, resMovies.getError());
        }
    }

    public List<MovieOverview> getMovieFromIntent() {
        return getActivity().getIntent()
            .getParcelableArrayListExtra(SEARCH_MOVIE_RESULT);
    }
}
