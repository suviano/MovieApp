package marcos.movieapp.movie;

import android.support.annotation.NonNull;
import android.util.Log;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.utils.RxUtils.unSubscribeOf;

class MoviePresenter implements ContractMovie.Presenter {

    static final String TAG = "Movie";
    @NonNull
    private final BaseSchedulerProvider schedulerProvider;
    @NonNull
    private MovieRepository repository;
    private Subscription subscription;
    @NonNull
    private ContractMovie.View view;
    @NonNull
    private String movieTitle;

    MoviePresenter(@NonNull MovieRepository repository,
                   @NonNull ContractMovie.View view,
                   @NonNull BaseSchedulerProvider schedulerProvider,
                   @NonNull String movieTitle) {
        this.repository = checkNotNull(repository, "MovieOverview repo is null");
        this.view = checkNotNull(view, "View is null");
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler is null");
        this.movieTitle = checkNotNull(movieTitle, "MovieOverview is null");

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        subscription = repository.getMovieByTitleId(movieTitle)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(new Observer<ResMovie>() {
                private ResMovie resMovie;

                @Override
                public void onCompleted() {
                    view.displaySearchResult(resMovie);
                }

                @Override
                public void onError(Throwable e) {
                    Log.wtf("MoviePresenter", e.getCause());
                }

                @Override
                public void onNext(ResMovie resMovie) {
                    this.resMovie = resMovie;
                }
            });
    }

    @Override
    public void unSubscribe() {
        unSubscribeOf(subscription);
    }

    @Override
    public void saveMovie(ResMovie resMovie) {
        repository.saveMovie(resMovie);
        view.operationComplete("Movie saved!");
    }

    @Override
    public void deleteMovie(String movieId) {
        boolean deleted = repository.deleteMovie(movieId);
        if (deleted) {
            view.operationComplete("Movie deleted");
        }
    }
}
