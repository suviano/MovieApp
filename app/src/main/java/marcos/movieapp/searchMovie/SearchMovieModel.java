package marcos.movieapp.searchMovie;

import marcos.movieapp.BaseModel;
import marcos.movieapp.Repository;
import marcos.movieapp.models.ShortMovie;
import rx.Observable;

// TODO
public class SearchMovieModel implements BaseModel {
    private Repository repository;

    public SearchMovieModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ShortMovie> result() {
        return null;
    }
}
