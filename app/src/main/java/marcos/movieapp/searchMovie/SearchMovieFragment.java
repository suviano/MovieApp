package marcos.movieapp.searchMovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import marcos.movieapp.BaseView;
import marcos.movieapp.R;
import marcos.movieapp.models.ShortMovie;

public class SearchMovieFragment extends Fragment implements BaseView {

    public SearchMovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void updateData(ShortMovie viewModel) {

    }
}
