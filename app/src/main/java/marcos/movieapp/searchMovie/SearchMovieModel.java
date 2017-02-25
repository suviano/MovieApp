package marcos.movieapp.searchMovie;

import marcos.movieapp.BaseModel;
import marcos.movieapp.Repository;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import rx.Observable;

public class SearchMovieModel implements BaseModel {
    private Repository repository;

    public SearchMovieModel(Repository repository) {
        this.repository = repository;
    }

    // TODO return Observable list of Movie
    @Override
    public Observable<OMDBResponse> result() {
        return repository.getMoviesFromData();
    }
}
