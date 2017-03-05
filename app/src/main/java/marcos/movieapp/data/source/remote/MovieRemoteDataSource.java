package marcos.movieapp.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.data.source.MovieDataSource;
import rx.Observable;

public class MovieRemoteDataSource implements MovieDataSource {

    @Nullable
    private static MovieRemoteDataSource INSTANCE = null;
    @NonNull
    private OMDBApiService omdbApiService;

    private MovieRemoteDataSource(@NonNull OMDBApiService omdbApiService) {
        this.omdbApiService = omdbApiService;
    }

    public static MovieRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieRemoteDataSource(new ApiMoviesSearch().provideService());
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<ResMovies> getMovies(@NonNull String name) {
        Observable<ResMovies> resMoviesObservable = omdbApiService.searchMovie(name);
        return resMoviesObservable.single(resMovies -> true);
    }

    @Override
    public Observable<ResMovie> getMovieByTitleId(@NonNull String titleId) {
        Observable<ResMovie> resMovieObservable = omdbApiService.searchMovieByIdOrTitle(titleId);
        return resMovieObservable.single(resMovie -> true);
    }

    @Override
    public void saveMovie(@NonNull ResMovie resMovie) {

    }

    @Override
    public boolean deleteMovie(@NonNull String movieTitle) {
        return false;
    }

}
