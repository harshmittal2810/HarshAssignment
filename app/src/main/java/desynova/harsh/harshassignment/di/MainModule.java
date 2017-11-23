package desynova.harsh.harshassignment.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import desynova.harsh.harshassignment.data.local.LocalRepository;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {
    @Provides
    @Singleton
    LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    CompositeDisposable provideCompositeSubscription() {
        return new CompositeDisposable();
    }
}
