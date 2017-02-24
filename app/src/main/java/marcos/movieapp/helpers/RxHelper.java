package marcos.movieapp.helpers;

import rx.Subscription;

public class RxHelper {
    public static void unSubscribe(Subscription subscription) {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
