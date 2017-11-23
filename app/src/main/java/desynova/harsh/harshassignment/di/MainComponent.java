package desynova.harsh.harshassignment.di;

import javax.inject.Singleton;

import dagger.Component;
import desynova.harsh.harshassignment.ui.component.dashboard.DashBoardFragment;
import desynova.harsh.harshassignment.ui.component.home.HomeFragment;
import desynova.harsh.harshassignment.ui.component.main.MainActivity;
import desynova.harsh.harshassignment.ui.component.notification.NotificationFragment;
import desynova.harsh.harshassignment.ui.component.splash.SplashActivity;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(HomeFragment fragment);

    void inject(DashBoardFragment fragment);

    void inject(NotificationFragment fragment);

}
