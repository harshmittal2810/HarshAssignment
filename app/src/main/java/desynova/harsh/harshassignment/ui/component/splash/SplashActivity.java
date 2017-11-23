package desynova.harsh.harshassignment.ui.component.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.ui.base.BaseActivity;
import desynova.harsh.harshassignment.ui.component.main.MainActivity;
import desynova.harsh.harshassignment.utils.ObjectUtil;

import static desynova.harsh.harshassignment.utils.Constants.SPLASH_DELAY;


public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter splashPresenter;
    @BindView(R.id.linearLayout)
    View view;

    @Override
    protected void initializeDagger() {
        DesynovaApp app = (DesynovaApp) getApplicationContext();
        app.getMainComponent().inject(SplashActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    protected void initializeCreate(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_layout;
    }

    @Override
    public void NavigateToMainScreen() {

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);

    }
}
