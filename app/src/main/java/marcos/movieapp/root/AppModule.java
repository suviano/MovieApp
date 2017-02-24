package marcos.movieapp.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mContext;

    public AppModule(Application mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return this.mContext;
    }
}
