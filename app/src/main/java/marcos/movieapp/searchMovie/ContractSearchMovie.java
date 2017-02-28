package marcos.movieapp.searchMovie;

import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;
import marcos.movieapp.data.entities.ResMovies;

interface ContractSearchMovie {
    interface Presenter extends BasePresenter {
        void searchMovie(String name);
    }

    interface View extends BaseView<Presenter> {
        void displaySearchResult(ResMovies resMovies);
    }
}
