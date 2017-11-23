package desynova.harsh.harshassignment.ui.component.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.ui.base.BaseActivity;
import desynova.harsh.harshassignment.ui.component.dashboard.DashBoardFragment;
import desynova.harsh.harshassignment.ui.component.home.HomeFragment;

public class MainActivity extends BaseActivity
        implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_toolbar_title)
    TextView txtToolbarTitle;
    @BindView(R.id.navigation)
    BottomNavigationView navigationView;


    @Override
    protected void initializeDagger() {
        DesynovaApp app = (DesynovaApp) getApplicationContext();
        app.getMainComponent().inject(MainActivity.this);

    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected void initializeCreate(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        switch (item.getItemId()) {
            case R.id.tab1:
                setToolBarTitle(getString(R.string.title_home));
                fragmentClass = HomeFragment.class;
                break;
            case R.id.tab2:
                setToolBarTitle(getString(R.string.title_dashboard));
                fragmentClass = DashBoardFragment.class;
                break;
            case R.id.tab3:
                setToolBarTitle(getString(R.string.title_notifications));
                fragmentClass = HomeFragment.class;
                break;

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        item.setChecked(true);
        setTitle(item.getTitle());

        return true;
    }

    @Override
    public void startActivity() {
        setSupportActionBar(toolbar);
        navigationView.setOnNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

    }

    @Override
    public void setToolBarTitle(String title) {
        toolbar.setTitle("");
        txtToolbarTitle.setText(title);
    }
}
