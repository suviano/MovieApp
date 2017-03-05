package marcos.movieapp.searchMovie;

import android.support.annotation.NonNull;

import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

class SearchMoviePresenter implements ContractSearchMovie.Presenter {

    final static String TAG = "SearchMovie";

    @NonNull
    private final MovieRepository movieRepository;

    @NonNull
    private final ContractSearchMovie.View view;

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;

    SearchMoviePresenter(@NonNull MovieRepository movieRepository,
                         @NonNull ContractSearchMovie.View view,
                         @NonNull BaseSchedulerProvider schedulerProvider) {
        this.movieRepository = checkNotNull(movieRepository, "MovieOverview repo is null");
        this.view = checkNotNull(view, "View is null");
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler is null");
        subscription = new CompositeSubscription();
        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        subscription.clear();
    }

    @Override
    public void unSubscribe() {
        subscription.clear();
    }

    public void searchMovies(String name) {
        this.subscription.add(movieRepository.getMovies(name).subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui()).subscribe(new Observer<ResMovies>() {
                private ResMovies resMovies;

                @Override
                public void onCompleted() {
                    view.displaySearchResult(resMovies);
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(ResMovies resMovies) {
                    this.resMovies = resMovies;
                }
            }));
    }
}
