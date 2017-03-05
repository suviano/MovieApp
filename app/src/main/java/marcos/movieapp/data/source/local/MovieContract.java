package marcos.movieapp.data.source.local;

import android.provider.BaseColumns;

final class MovieContract {
    private MovieContract() {
    }

    static abstract class MovieEntry implements BaseColumns {
        static final String TABLE_NAME = "MOVIES";
        static final String COLUMN_TITLE = "TITLE";
        static final String COLUMN_YEAR = "YEAR";
        static final String COLUMN_RATED = "RATED";
        static final String COLUMN_RELEASED = "RELEASED";
        static final String COLUMN_RUNTIME = "RUNTIME";
        static final String COLUMN_GENRE = "GENRE";
        static final String COLUMN_DIRECTOR = "DIRECTOR";
        static final String COLUMN_WRITER = "WRITER";
        static final String COLUMN_ACTORS = "ACTORS";
        static final String COLUMN_PLOT = "PLOT";
        static final String COLUMN_LANGUAGE = "LANGUAGE";
        static final String COLUMN_COUNTRY = "COUNTRY";
        static final String COLUMN_AWARDS = "AWARDS";
        static final String COLUMN_POSTER = "POSTER";
        static final String COLUMN_METASCORE = "METASCORE";
        static final String COLUMN_IMDB_RATING = "IMDB_RATING";
        static final String COLUMN_IMDB_VOTES = "IMDB_VOTES";
        static final String COLUMN_IMDB_ID = "IMDB_ID";
        static final String COLUMN_TYPE = "TYPE";
        static final String COLUMN_TOTAL_SEASONS = "TOTAL_SEASONS";
    }
}
