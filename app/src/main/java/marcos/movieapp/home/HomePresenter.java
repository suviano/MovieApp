package marcos.movieapp.home;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import marcos.movieapp.data.entities.ResMovie;
import marcos.movieapp.data.entities.ResMovies;
import marcos.movieapp.data.source.MovieRepository;
import marcos.movieapp.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

class HomePresenter implements Contract.Presenter {

    static final String TAG = "Movies";

    @NonNull
    private final MovieRepository movieRepository;

    @NonNull
    private final Contract.View view;
    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;

    HomePresenter(@NonNull MovieRepository movieRepository, @NonNull Contract.View view,
                  @NonNull BaseSchedulerProvider schedulerProvider) {
        this.movieRepository = checkNotNull(movieRepository, "MovieOverview repo is null");
        this.view = checkNotNull(view, "View is null");
        this.schedulerProvider = checkNotNull(schedulerProvider, "Scheduler is null");

        subscription = new CompositeSubscription();
        this.view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        this.subscription.clear();
        Subscription subscription = movieRepository.getFavoriteMovies()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(this::displayFav);
        this.subscription.add(subscription);
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

                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    Log.wtf(TAG, "Check connection | Error: " + e.getCause());
                }

                @Override
                public void onNext(ResMovies resMovies) {
                    view.displaySearchResult(resMovies);
                }
            }));
    }

    private void displayFav(List<ResMovie> resMovies) {
        if (!resMovies.isEmpty()) {
            view.displayFavorites(resMovies);
        }
    }

}
