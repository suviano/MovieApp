package marcos.movieapp.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.data.source.MovieDataSource;
import rx.Observable;


public class MovieLocalDataSource implements MovieDataSource {
    @Nullable
    private static MovieLocalDataSource INSTANCE = null;

    private MovieLocalDataSource() {
    }

    public static MovieLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieLocalDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<ResMovies> getMovies(@NonNull String name) {
        return null;
    }

    @Override
    public Observable<ResMovie> getMovieByTitleId(@NonNull String titleId) {
        return null;
    }

    @Override
    public void saveMovie(@NonNull ResMovie resMovie) {

    }
}
