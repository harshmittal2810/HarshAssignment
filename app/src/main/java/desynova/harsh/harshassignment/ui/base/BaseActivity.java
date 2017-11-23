package desynova.harsh.harshassignment.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.ui.base.listeners.ActionBarView;
import desynova.harsh.harshassignment.ui.base.listeners.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView,
        ActionBarView {

    private final String TAG = BaseActivity.class.getSimpleName();
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    protected Presenter presenter;
    private Unbinder unbinder;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    protected abstract void initializeCreate(Bundle savedInstanceState);

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);

        initializeDagger();
        initializePresenter();
        initializeToolbar();
        initializeCreate(savedInstanceState);
        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    protected void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }
    }

    @Override
    public void setUpIconVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(visible);
        }
    }

    @Override
    public void setTitle(String titleKey) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            TextView title = ButterKnife.findById(this, R.id.txt_toolbar_title);
            if (title != null) {
                title.setText(titleKey);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
