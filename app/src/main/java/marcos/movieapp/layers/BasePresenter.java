package marcos.movieapp.layers;

public interface BasePresenter {
    void loadData();

    void rxUnSubscribe();

    void setView(BaseView view);
}
