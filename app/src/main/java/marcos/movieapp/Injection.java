package marcos.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;

import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.data.source.local.MovieLocalDataSource;
import marcos.movieapp.data.source.remote.MovieRemoteDataSource;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import marcos.movieapp.utils.schedulers.SchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static MovieRepository provideMovieRepository(@NonNull Context context) {
        checkNotNull(context);
        return MovieRepository.getInstance(
            MovieRemoteDataSource.getInstance(), MovieLocalDataSource.getInstance());
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
