package marcos.movieapp.apiHandlers.services;

import marcos.movieapp.models.OMDBapi.OMDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApi {
    @GET("/")
    Call<OMDBResponse> searchMovies(@Query("s") String title);
}
