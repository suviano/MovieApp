package marcos.movieapp.home;

import android.support.annotation.NonNull;
import android.util.Log;

import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

class MoviesPresenter implements Contract.Presenter {

    static final String TAG = "Movies";

    @NonNull
    private final MovieRepository movieRepository;

    @NonNull
    private final Contract.View view;
    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;

    MoviesPresenter(@NonNull MovieRepository movieRepository, @NonNull Contract.View view,
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
        this.subscription.add(movieRepository.getMovies(name.toLowerCase())
            .subscribeOn(schedulerProvider.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ResMovies>() {
                private ResMovies resMovies;

                @Override
                public void onCompleted() {
                    view.displaySearchResult(resMovies);
                }

                @Override
                public void onError(Throwable e) {
                    Log.wtf(TAG, e);
                }

                @Override
                public void onNext(ResMovies resMovies) {
                    this.resMovies = resMovies;
                }
            }));
    }

}
