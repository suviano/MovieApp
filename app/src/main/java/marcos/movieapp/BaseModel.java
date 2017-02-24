package marcos.movieapp;

import marcos.movieapp.models.ShortMovie;
import rx.Observable;


public interface BaseModel {
    Observable<ShortMovie> result();
}
