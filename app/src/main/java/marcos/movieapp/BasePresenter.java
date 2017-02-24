package marcos.movieapp;

public interface BasePresenter {
    void loadData();

    void rxUnSubscribe();

    void setView(BaseView view);
}
