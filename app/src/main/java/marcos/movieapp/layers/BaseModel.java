package marcos.movieapp.layers;

import marcos.movieapp.models.OMDBapi.OMDBResponse;
import rx.Observable;


public interface BaseModel {
    Observable<OMDBResponse> result();
}
