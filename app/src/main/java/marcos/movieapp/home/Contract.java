package marcos.movieapp.home;

import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;
import marcos.movieapp.data.entities.ResMovies;

interface Contract {
    interface Presenter extends BasePresenter {
        void searchMovie(String name);
    }

    interface View extends BaseView<Presenter> {
        void displaySearchResult(ResMovies resMovies);
    }
}
