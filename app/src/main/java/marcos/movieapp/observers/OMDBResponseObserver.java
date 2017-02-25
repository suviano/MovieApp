package marcos.movieapp.observers;

import marcos.movieapp.models.OMDBapi.OMDBResponse;
import rx.Observer;

public abstract class OMDBResponseObserver implements Observer<OMDBResponse> {

    public void onCompleted() {

    }

    public void onError(Throwable e) {
        e.printStackTrace();
        /*if (baseView != null) {
            // TODO Show error message
        }*/
    }

    // I want handle logic here but i don't know how! `me sad`
    // default java 8 do not work with dagger :(
    public abstract void onNext(OMDBResponse response);
}
