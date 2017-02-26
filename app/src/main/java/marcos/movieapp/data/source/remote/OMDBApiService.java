package marcos.movieapp.data.source.remote;


import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

interface OMDBApiService {

    @GET("/")
    Observable<ResMovies> searchMovie(@Query("s") String title);

    @GET("/")
    Observable<ResMovie> searchMovieByIdOrTitle(@Query("t") String query);
}
