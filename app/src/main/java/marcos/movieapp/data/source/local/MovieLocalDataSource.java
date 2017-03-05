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
import static marcos.movieapp.data.source.local.MovieContract.MovieEntry;


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
        contentValues.put(MovieEntry.COLUMN_TITLE, resMovie.getTitle());
        contentValues.put(MovieEntry.COLUMN_YEAR, resMovie.getTyar());
        contentValues.put(MovieEntry.COLUMN_RATED, resMovie.getRated());
        contentValues.put(MovieEntry.COLUMN_RELEASED, resMovie.getReleased());
        contentValues.put(MovieEntry.COLUMN_RUNTIME, resMovie.getRuntime());
        contentValues.put(MovieEntry.COLUMN_GENRE, resMovie.getGenre());
        contentValues.put(MovieEntry.COLUMN_DIRECTOR, resMovie.getDirector());
        contentValues.put(MovieEntry.COLUMN_WRITER, resMovie.getWriter());
        contentValues.put(MovieEntry.COLUMN_ACTORS, resMovie.getActors());
        contentValues.put(MovieEntry.COLUMN_PLOT, resMovie.getPlot());
        contentValues.put(MovieEntry.COLUMN_LANGUAGE, resMovie.getLanguage());
        contentValues.put(MovieEntry.COLUMN_COUNTRY, resMovie.getCountry());
        contentValues.put(MovieEntry.COLUMN_AWARDS, resMovie.getAwards());
        contentValues.put(MovieEntry.COLUMN_POSTER, resMovie.getPoster());
        contentValues.put(MovieEntry.COLUMN_METASCORE, resMovie.getMetaScore());
        contentValues.put(MovieEntry.COLUMN_IMDB_RATING, resMovie.getImdbRating());
        contentValues.put(MovieEntry.COLUMN_IMDB_VOTES, resMovie.getImdbVotes());
        contentValues.put(MovieEntry.COLUMN_IMDB_ID, resMovie.getImdbID());
        contentValues.put(MovieEntry.COLUMN_TYPE, resMovie.getType());
        contentValues.put(MovieEntry.COLUMN_TOTAL_SEASONS, resMovie.getTotalSeasons());
        briteDatabase.insert(MovieEntry.TABLE_NAME,
            contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public boolean deleteMovie(@NonNull String movieId) {
        String selection = MovieEntry.COLUMN_IMDB_ID + " LIKE ?";
        return 1 == briteDatabase.delete(MovieEntry.TABLE_NAME, selection, movieId);
    }
}
