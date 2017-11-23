package desynova.harsh.harshassignment.ui.component.home;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.ui.base.BaseFragment;
import desynova.harsh.harshassignment.ui.base.listeners.RecyclerItemListener;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by harshmittal on 23/11/17.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, ImageAdapter.Listener, RecyclerItemListener {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.rl_list)
    RelativeLayout rlList;
    @BindView(R.id.textEmptyList)
    TextView textEmptyList;
    @BindView(R.id.imageView1)
    AppCompatImageView imageView;

    @Override
    public void showMessage(String msg) {
        Snackbar.make(rlList, msg, LENGTH_SHORT).show();

    }

    @Override
    protected void initializeDagger() {
        DesynovaApp desynovaApp = (DesynovaApp) getActivity().getApplicationContext();
        desynovaApp.getMainComponent().inject(HomeFragment.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void startActivity() {

    }

    @Override
    public void initializeList1(TabOne tabOne) {
        ImageAdapter imageVideoAdapter = new ImageAdapter(getActivity(), tabOne.getData(), this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(imageVideoAdapter);
    }

    @Override
    public void initializeList2(TabOne tabOne) {
        ImageAdapter imageVideoAdapter = new ImageAdapter(getActivity(), tabOne.getData(), this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(imageVideoAdapter);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {
        tvNoData.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        rlList.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setEmptyList(boolean visibility) {
        textEmptyList.setVisibility(visibility ? View.VISIBLE : View.GONE);
        recyclerView2.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onItemSelected(int position, String url) {
        if (url != null)
            Glide.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
    }
}
