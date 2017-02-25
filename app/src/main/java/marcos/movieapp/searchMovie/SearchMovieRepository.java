package marcos.movieapp.searchMovie;

import marcos.movieapp.Repository;
import marcos.movieapp.apiHandlers.services.OMDBApiService;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import rx.Observable;
import rx.functions.Func1;

public class SearchMovieRepository implements Repository {
    private static final long STALE_MS = 20 * 1000;
    private OMDBApiService omdbApiService;
    private OMDBResponse moviesList;
    private long timestamp;

    public SearchMovieRepository(OMDBApiService omdbApiService) {
        this.omdbApiService = omdbApiService;
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    // TODO
    @Override
    public Observable<OMDBResponse> getMoviesFromMemory() {
        if (isUpToDate()) {
            //return omdbApiService.searchMovies("spider man");
            return null;
        } else {
            timestamp = System.currentTimeMillis();
            return Observable.empty();
        }
    }

    // TODO Get movies from response
    @Override
    public Observable<OMDBResponse> searchMoviesOfNetwork() {
        Observable<OMDBResponse> omdbResponseObservable = omdbApiService.searchMovies("spider man");
        return omdbResponseObservable.single(new Func1<OMDBResponse, Boolean>() {
            @Override
            public Boolean call(OMDBResponse response) {
                moviesList = response;
                return true;
            }
        });
    }

    @Override
    public Observable<OMDBResponse> getMoviesFromData() {
        return getMoviesFromMemory().switchIfEmpty(searchMoviesOfNetwork());
    }
}
