package marcos.movieapp.searchMovie;

import marcos.movieapp.helpers.RxHelper;
import marcos.movieapp.layers.BaseModel;
import marcos.movieapp.layers.BasePresenter;
import marcos.movieapp.layers.BaseView;
import marcos.movieapp.models.OMDBapi.MovieOnList;
import marcos.movieapp.models.OMDBapi.OMDBResponse;
import marcos.movieapp.observers.OMDBResponseObserver;
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
            .subscribe(new OMDBResponseObserver() {
                @Override
                public void onNext(OMDBResponse response) {
                    baseView.updateData(response);
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
