package marcos.movieapp.searchMovie;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import marcos.movieapp.BaseModel;
import marcos.movieapp.BasePresenter;
import marcos.movieapp.Repository;
import marcos.movieapp.apiHandlers.modules.OMDBConnProvider;
import marcos.movieapp.apiHandlers.services.OMDBApi;

@Module(includes = {OMDBConnProvider.class})
public class SearchMovieModule {
    @Provides
    public BasePresenter providePresenter(BaseModel baseModel) {
        return new SearchMoviePresenter(baseModel);
    }

    @Provides
    @Singleton
    public Repository provideRepo(OMDBApi apiService) {
        return new SearchMovieRepository(apiService);
    }

    @Provides
    public BaseModel provideModel(Repository repo) {
        return new SearchMovieModel(repo);
    }
}
