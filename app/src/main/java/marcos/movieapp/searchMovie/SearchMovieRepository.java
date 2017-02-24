package marcos.movieapp.searchMovie;

import java.util.ArrayList;
import java.util.List;

import marcos.movieapp.Repository;
import marcos.movieapp.apiHandlers.services.OMDBApi;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import marcos.movieapp.models.ShortMovie;
import rx.Observable;

// TODO
public class SearchMovieRepository implements Repository {
    private OMDBApi omdbApi;
    private List<ShortMovie> movieList;

    public SearchMovieRepository(OMDBApi omdbApi) {
        this.omdbApi = omdbApi;
        this.movieList = new ArrayList<>();
    }

    @Override
    public Observable<OMDBResponse> getMoviesFromMemory() {
        //return Observable.from(movieList);
        return null;
    }

    @Override
    public Observable<OMDBResponse> getMoviesFromNetwork() {
        return null;
    }

    @Override
    public Observable<OMDBResponse> getMoviesFromData() {
        return null;
    }
}
