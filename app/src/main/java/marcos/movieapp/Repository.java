package marcos.movieapp;

import marcos.movieapp.models.OMDBapi.OMDBResponse;
import rx.Observable;

public interface Repository {
    Observable<OMDBResponse> getMoviesFromMemory();

    Observable<OMDBResponse> searchMoviesOfNetwork();

    Observable<OMDBResponse> getMoviesFromData();
}
