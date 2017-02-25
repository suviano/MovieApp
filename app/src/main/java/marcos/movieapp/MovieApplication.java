package marcos.movieapp;

import android.app.Application;

import marcos.movieapp.apiHandlers.modules.OMDBProviderModule;
import marcos.movieapp.searchMovie.SearchMovieComponent;
import marcos.movieapp.searchMovie.SearchMovieModule;
import marcos.movieapp.searchMovie.DaggerSearchMovieComponent;

public class MovieApplication extends Application {
    private SearchMovieComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerSearchMovieComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .searchMovieModule(new SearchMovieModule())
            .oMDBProviderModule(new OMDBProviderModule())
            .build();
    }

    public SearchMovieComponent getComponent() {
        return component;
    }
}
