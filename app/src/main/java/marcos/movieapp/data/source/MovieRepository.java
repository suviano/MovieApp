package marcos.movieapp.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class MovieRepository implements MovieDataSource {

    @Nullable
    private static MovieRepository INSTANCE = null;
    @NonNull
    private final MovieDataSource remoteMovieDataSource;
    @NonNull
    private final MovieDataSource localMovieDataSource;

    private MovieRepository(
        @NonNull MovieDataSource remoteDataSource, @NonNull MovieDataSource localDataSource) {
        this.localMovieDataSource = checkNotNull(localDataSource);
        this.remoteMovieDataSource = checkNotNull(remoteDataSource);
    }

    public static MovieRepository getInstance(
        @NonNull MovieDataSource remoteDataSource, @NonNull MovieDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<ResMovies> getMovies(@NonNull String name) {
        return remoteMovieDataSource.getMovies(name);
    }

    @Override
    public Observable<ResMovie> getMovieByTitleId(@NonNull String titleId) {
        return remoteMovieDataSource.getMovieByTitleId(titleId)
            .filter(resMovie -> resMovie.getType().equalsIgnoreCase("movie"));
    }

    @Override
    public void saveMovie(@NonNull ResMovie resMovie) {
        localMovieDataSource.saveMovie(resMovie);
    }

    @Override
    public void deleteMovie(@NonNull String movieTitle) {
        localMovieDataSource.deleteMovie(movieTitle);
    }

}
