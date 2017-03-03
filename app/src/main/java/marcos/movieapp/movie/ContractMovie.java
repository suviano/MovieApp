package marcos.movieapp.movie;

import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;
import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovie;

interface ContractMovie {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void displaySearchResult(ResMovie resMovie);

        MovieOverview getMovieFromIntent();
    }
}
