package marcos.movieapp;

import marcos.movieapp.models.OMDBapi.OMDBResponse;

public interface BaseView {

    void updateData(OMDBResponse omdbResponse);

}
