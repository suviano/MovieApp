package marcos.movieapp.movies;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import static com.google.common.base.Preconditions.checkNotNull;

public class MovieFragment extends Fragment implements Contract.View {

    private Contract.Presenter presenter;

    public MovieFragment() {
    }

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public void setPresenter(@NonNull Contract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.presenter.unSubscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.unSubscribe();
    }
}
