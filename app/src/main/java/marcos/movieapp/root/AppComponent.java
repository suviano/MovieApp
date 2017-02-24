package marcos.movieapp.root;

import javax.inject.Singleton;

import dagger.Component;
import marcos.movieapp.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
