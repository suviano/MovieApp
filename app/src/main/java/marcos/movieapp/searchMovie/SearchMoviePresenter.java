package marcos.movieapp.searchMovie;

import marcos.movieapp.BaseModel;
import marcos.movieapp.BasePresenter;
import marcos.movieapp.BaseView;
import marcos.movieapp.observers.ShortMovieObserver;
import marcos.movieapp.helpers.RxHelper;
import marcos.movieapp.models.ShortMovie;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchMoviePresenter implements BasePresenter {
    private BaseView baseView;
    private BaseModel baseModel;
    private Subscription rxSubscription = null;

    public SearchMoviePresenter(BaseModel model) {
        this.baseModel = model;
    }

    @Override
    public void loadData() {
        this.rxSubscription = baseModel.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShortMovieObserver() {
                    @Override
                    public void onNext(ShortMovie shortMovie) {
                        baseView.updateData(shortMovie);
                    }
                });
    }

    @Override
    public void rxUnSubscribe() {
        RxHelper.unSubscribe(rxSubscription);
    }

    @Override
    public void setView(BaseView baseView) {
        this.baseView = baseView;
    }
}
