package marcos.movieapp.layers;

import marcos.movieapp.models.OMDBapi.MovieOnList;
import marcos.movieapp.models.OMDBapi.OMDBResponse;

public interface BaseView {

    void updateData(OMDBResponse omdbResponse);

}
