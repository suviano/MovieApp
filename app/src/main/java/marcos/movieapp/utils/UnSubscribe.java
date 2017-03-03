package marcos.movieapp.utils;

import android.support.annotation.Nullable;

import rx.Subscription;

public class UnSubscribe {
    public static void unSubscribeOf(@Nullable Subscription subscription) {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
