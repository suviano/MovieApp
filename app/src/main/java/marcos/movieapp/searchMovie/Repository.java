package marcos.movieapp.searchMovie;

import marcos.movieapp.models.OMDBapi.MovieOnList;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import rx.Observable;

public interface Repository {
    Observable<OMDBResponse> getMoviesFromMemory();

    Observable<OMDBResponse> searchMoviesOfNetwork();

    Observable<OMDBResponse> getMoviesFromData();
}
