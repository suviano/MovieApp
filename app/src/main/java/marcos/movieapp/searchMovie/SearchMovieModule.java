package marcos.movieapp.searchMovie;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import marcos.movieapp.apiHandlers.modules.OMDBProviderModule;
import marcos.movieapp.layers.BaseModel;
import marcos.movieapp.layers.BasePresenter;
import marcos.movieapp.apiHandlers.services.OMDBApiService;

@Module(includes = {OMDBProviderModule.class})
public class SearchMovieModule {
    @Provides
    public BasePresenter providePresenter(BaseModel baseModel) {
        return new SearchMoviePresenter(baseModel);
    }

    @Provides
    @Singleton
    public Repository provideRepo(OMDBApiService apiService) {
        return new SearchMovieRepository(apiService);
    }

    @Provides
    public BaseModel provideModel(Repository repo) {
        return new SearchMovieModel(repo);
    }
}
