package marcos.movieapp.searchMovie;

import android.support.annotation.NonNull;

import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

class SearchMoviePresenter implements ContractSearchMovie.Presenter {

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

    public void searchMovie(String name) {
        this.subscription.add(movieRepository.getMovies(name).subscribeOn(schedulerProvider.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(view::displaySearchResult));
    }
}
