package marcos.movieapp.observers;

import marcos.movieapp.models.ShortMovie;
import rx.Observer;

public abstract class ShortMovieObserver implements Observer<ShortMovie> {

    public void onCompleted() {

    }

    public void onError(Throwable e) {
        e.printStackTrace();
        /*if (baseView != null) {
            // TODO Show error message
        }*/
    }

    // I want handle logic here but i don't know how! `me sad`
    public abstract void onNext(ShortMovie shortMovie);
}
