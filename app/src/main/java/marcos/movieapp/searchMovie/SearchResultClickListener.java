package marcos.movieapp.searchMovie;

import android.view.View;

import marcos.movieapp.data.entities.MovieOverview;

public interface SearchResultClickListener {
    void seeDetails(View view, MovieOverview movieOverview);
}
