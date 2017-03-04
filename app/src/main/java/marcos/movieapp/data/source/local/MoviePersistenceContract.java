package marcos.movieapp.data.source.local;

import android.provider.BaseColumns;

public final class MoviePersistenceContract {
    private MoviePersistenceContract () {}

    public static abstract class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "";
        public static final String COLUMN_TITLE = "TITLE";
        public static final String COLUMN_YEAR = "YEAR";
        public static final String COLUMN_RATED = "RATED";
        public static final String COLUMN_RELEASED = "RELEASED";
        public static final String COLUMN_RUNTIME = "RUNTIME";
        public static final String COLUMN_GENRE = "GENRE";
        public static final String COLUMN_DIRECTOR = "DIRECTOR";
        public static final String COLUMN_WRITER = "WRITER";
        public static final String COLUMN_ACTORS = "ACTORS";
        public static final String COLUMN_PLOT = "PLOT";
        public static final String COLUMN_LANGUAGE = "LANGUAGE";
        public static final String COLUMN_COUNTRY = "COUNTRY";
        public static final String COLUMN_AWARDS = "AWARDS";
        public static final String COLUMN_POSTER = "POSTER";
        public static final String COLUMN_METASCORE = "METASCORE";
        public static final String COLUMN_IMDB_RATING = "IMDB_RATING";
        public static final String COLUMN_IMDB_VOTES = "IMDB_VOTES";
        public static final String COLUMN_IMDB_ID = "IMDB_ID";
        public static final String COLUMN_TYPE = "TYPE";
        public static final String COLUMN_TOTAL_SEASONS = "TOTAL_SEASONS";
        public static final String COLUMN_RESPONSE = "RESPONSE";
    }
}
