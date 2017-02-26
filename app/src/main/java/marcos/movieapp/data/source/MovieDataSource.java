package marcos.movieapp.data.source;


import android.support.annotation.NonNull;

import java.util.List;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import rx.Observable;

public interface MovieDataSource {
    Observable<ResMovies> getMovies(@NonNull String name);

    Observable<ResMovie> getMovieByTitleId(@NonNull String titleId);

    void saveMovie(@NonNull ResMovie resMovie);
}
