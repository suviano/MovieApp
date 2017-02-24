package marcos.movieapp.root;

import javax.inject.Singleton;

import dagger.Component;
import marcos.movieapp.MainActivity;
import marcos.movieapp.apiHandlers.modules.OMDBProviderModule;
import marcos.movieapp.searchMovie.SearchMovieModule;

@Singleton
@Component(modules = {
    AppModule.class, SearchMovieModule.class, OMDBProviderModule.class
})
public interface AppComponent {
    void inject(MainActivity target);
}
