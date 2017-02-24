package marcos.movieapp.apiHandlers.services;

import marcos.movieapp.models.OMDBapi.OMDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OMDBApiService {
    @GET("/")
    public Observable<OMDBResponse> searchMovies(@Query("s") String title);
}
