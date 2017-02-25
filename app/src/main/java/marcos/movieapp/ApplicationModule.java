package marcos.movieapp;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mContext;

    public ApplicationModule(Application mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return this.mContext;
    }
}
