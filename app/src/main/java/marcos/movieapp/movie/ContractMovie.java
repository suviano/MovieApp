package marcos.movieapp.movie;

import android.support.annotation.NonNull;

import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovie;

interface ContractMovie {
    interface Presenter extends BasePresenter {
        void saveMovie(ResMovie resMovie);

        void deleteMovie(String movieTitle);
    }

    interface View extends BaseView<Presenter> {
        void displaySearchResult(ResMovie resMovie);

        MovieOverview getMovieFromIntent();

        void configureMovieTitle(@NonNull String title);

        void operationComplete(@NonNull String operation);
    }
}
