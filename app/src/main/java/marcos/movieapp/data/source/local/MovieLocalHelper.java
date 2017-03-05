package marcos.movieapp.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MovieLocalHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Movie.db";
    private static final String TEXT_TYPE = " TEXT";

    private static final String SQL_CREATE_ENTRIES =
        String.format("CREATE TABLE %s (%s %s PRIMARY KEY, " +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s ,\n" +
                "%s %s" +
                " )",
            MovieContract.MovieEntry.TABLE_NAME,
            MovieContract.MovieEntry.COLUMN_IMDB_ID, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_TITLE, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_YEAR, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_RATED, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_RELEASED, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_RUNTIME, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_GENRE, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_DIRECTOR, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_WRITER, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_ACTORS, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_PLOT, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_LANGUAGE, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_COUNTRY, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_AWARDS, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_POSTER, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_METASCORE, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_IMDB_RATING, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_IMDB_VOTES, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_TYPE, TEXT_TYPE,
            MovieContract.MovieEntry.COLUMN_TOTAL_SEASONS, TEXT_TYPE
        );

    MovieLocalHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
