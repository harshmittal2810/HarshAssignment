package desynova.harsh.harshassignment.ui.component.dashboard;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.base.BaseFragment;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by harshmittal on 23/11/17.
 */

public class DashBoardFragment extends BaseFragment implements DashContract.View {
    @Inject
    DashPresenter presenter;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.bgImage)
    AppCompatImageView imageView;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    @Override
    public void showMessage(String msg) {
        Snackbar.make(imageView, msg, LENGTH_SHORT).show();

    }

    @Override
    protected void initializeDagger() {
        DesynovaApp desynovaApp = (DesynovaApp) getActivity().getApplicationContext();
        desynovaApp.getMainComponent().inject(DashBoardFragment.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void startActivity() {

    }

    @Override
    public void initializeList(TabTwo tabTwo) {
        if (isVisible() && isAdded()) {

            if (tabTwo.getData().getBgurl() != null)
                Glide.with(imageView.getContext())
                        .load(tabTwo.getData().getBgurl())
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imageView);

            DashBoardAdapter dashBoardAdapter1 = new DashBoardAdapter(getActivity(), tabTwo);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView1.setLayoutManager(layoutManager1);
            recyclerView1.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
            recyclerView1.setHasFixedSize(true);
            recyclerView1.setAdapter(dashBoardAdapter1);
        }

    }


    @Override
    public void setLoaderVisibility(boolean isVisible) {
        if (isVisible() && isAdded()) {
            pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
        }
    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {
        if (isVisible() && isAdded()) {
            tvNoData.setVisibility(isVisible ? VISIBLE : GONE);
        }
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        if (isVisible() && isAdded()) {
            imageView.setVisibility(isVisible ? VISIBLE : GONE);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
