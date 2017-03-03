package marcos.movieapp.movie;

import android.support.annotation.NonNull;

import marcos.movieapp.data.entities.MovieOverview;
import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;

import static com.google.common.base.Preconditions.checkNotNull;
import static marcos.movieapp.utils.UnSubscribe.unSubscribeOf;

class MoviePresenter implements ContractMovie.Presenter {

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;
    @NonNull
    private MovieRepository repository;
    private Subscription subscription;
    @NonNull
    private ContractMovie.View view;
    @NonNull
    private MovieOverview movieOverview;

    MoviePresenter(@NonNull MovieRepository repository,
                   @NonNull ContractMovie.View view,
                   @NonNull BaseSchedulerProvider schedulerProvider,
                   @NonNull MovieOverview movieOverview) {
        this.repository = checkNotNull(repository, "MovieOverview repo is null");
        this.view = checkNotNull(view, "View is null");
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler is null");
        this.movieOverview = checkNotNull(movieOverview, "MovieOverview is null");

        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        getMovieInfo();
    }

    @Override
    public void unSubscribe() {
        unSubscribeOf(subscription);
    }

    private void getMovieInfo() {
        subscription = repository.getMovieByTitleId(movieOverview.getTitle())
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
                    e.printStackTrace();
                }

                @Override
                public void onNext(ResMovie resMovie) {
                    this.resMovie = resMovie;
                }
            });
    }
}
