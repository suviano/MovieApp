package marcos.movieapp.movies;

import android.support.annotation.NonNull;

import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

class MoviePresenter implements Contract.Presenter {

    @NonNull
    private final MovieRepository movieRepository;

    @NonNull
    private final Contract.View view;

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;

    MoviePresenter(@NonNull MovieRepository movieRepository, @NonNull Contract.View view,
                   @NonNull BaseSchedulerProvider schedulerProvider) {
        this.movieRepository = checkNotNull(movieRepository, "Movie repo is null");
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
        this.subscription.add(movieRepository
            .getMovies(name)
            .subscribeOn(schedulerProvider.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showResult));
    }
}
