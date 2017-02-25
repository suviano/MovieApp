package marcos.movieapp.movies;

import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;

interface Contract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
