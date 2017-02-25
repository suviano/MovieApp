package marcos.movieapp.searchMovie;

import javax.inject.Singleton;

import dagger.Component;
import marcos.movieapp.ApplicationModule;
import marcos.movieapp.searchMovie.SearchMovieActivity;
import marcos.movieapp.apiHandlers.modules.OMDBProviderModule;
import marcos.movieapp.searchMovie.SearchMovieModule;

@Singleton
@Component(modules = {
    ApplicationModule.class, SearchMovieModule.class, OMDBProviderModule.class
})
public interface SearchMovieComponent {
    void inject(SearchMovieActivity target);
}
