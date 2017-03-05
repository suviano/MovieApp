package marcos.movieapp.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.data.source.MovieDataSource;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;


public class MovieLocalDataSource implements MovieDataSource {
    @Nullable
    private static MovieLocalDataSource INSTANCE = null;

    @NonNull
    private final BriteDatabase briteDatabase;

    private MovieLocalDataSource(@NonNull Context context,
                                 @NonNull BaseSchedulerProvider schedulerProvider) {
        checkNotNull(context, "context cannot be null");
        checkNotNull(schedulerProvider, "schedulerProvider cannot be null");
        MovieLocalHelper movieLocalHelper = new MovieLocalHelper(context);
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        briteDatabase = sqlBrite.wrapDatabaseHelper(movieLocalHelper, schedulerProvider.io());
    }

    public static MovieLocalDataSource getInstance(@NonNull Context context,
                                                   @NonNull BaseSchedulerProvider schedulerProvider) {
        if (INSTANCE == null) {
            INSTANCE = new MovieLocalDataSource(context, schedulerProvider);
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
        checkNotNull(resMovie);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry._ID, resMovie.get_id());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, resMovie.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_YEAR, resMovie.getTyar());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RATED, resMovie.getRated());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASED, resMovie.getReleased());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RUNTIME, resMovie.getRuntime());
        contentValues.put(MovieContract.MovieEntry.COLUMN_GENRE, resMovie.getGenre());
        contentValues.put(MovieContract.MovieEntry.COLUMN_DIRECTOR, resMovie.getDirector());
        contentValues.put(MovieContract.MovieEntry.COLUMN_WRITER, resMovie.getWriter());
        contentValues.put(MovieContract.MovieEntry.COLUMN_ACTORS, resMovie.getActors());
        contentValues.put(MovieContract.MovieEntry.COLUMN_PLOT, resMovie.getPlot());
        contentValues.put(MovieContract.MovieEntry.COLUMN_LANGUAGE, resMovie.getLanguage());
        contentValues.put(MovieContract.MovieEntry.COLUMN_COUNTRY, resMovie.getCountry());
        contentValues.put(MovieContract.MovieEntry.COLUMN_AWARDS, resMovie.getAwards());
        contentValues.put(MovieContract.MovieEntry.COLUMN_POSTER, resMovie.getPoster());
        contentValues.put(MovieContract.MovieEntry.COLUMN_METASCORE, resMovie.getMetaScore());
        contentValues.put(MovieContract.MovieEntry.COLUMN_IMDB_RATING, resMovie.getImdbRating());
        contentValues.put(MovieContract.MovieEntry.COLUMN_IMDB_VOTES, resMovie.getImdbVotes());
        contentValues.put(MovieContract.MovieEntry.COLUMN_IMDB_ID, resMovie.getImdbID());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TYPE, resMovie.getType());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TOTAL_SEASONS, resMovie.getTotalSeasons());
        briteDatabase.insert(MovieContract.MovieEntry.TABLE_NAME,
            contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void deleteMovie(@NonNull String movieTitle) {
        // TODO: delete movie from db
    }
}
