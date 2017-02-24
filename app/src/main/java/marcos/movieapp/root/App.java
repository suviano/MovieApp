package marcos.movieapp.root;

import android.app.Application;

import marcos.movieapp.apiHandlers.modules.OMDBProviderModule;
import marcos.movieapp.searchMovie.SearchMovieModule;

public class App extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .searchMovieModule(new SearchMovieModule())
                .oMDBProviderModule(new OMDBProviderModule())
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
