package desynova.harsh.harshassignment.ui.component.dashboard;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
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
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;
    @BindView(R.id.recyclerView4)
    RecyclerView recyclerView4;
    @BindView(R.id.bgImage)
    AppCompatImageView imageView;
    @BindView(R.id.textView1)
    AppCompatTextView textView;
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
        if (tabTwo.getData().getBgurl() != null)
            Glide.with(imageView.getContext())
                    .load(tabTwo.getData().getBgurl())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);

        textView.setText(tabTwo.getData().getDescription());
        ImageAdapter imageAdapter1 = new ImageAdapter(tabTwo.getData().getRv1());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(imageAdapter1);


        ImageAdapter imageAdapter2 = new ImageAdapter(tabTwo.getData().getRv2());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(imageAdapter2);

        ImageAdapter imageAdapter3 = new ImageAdapter(tabTwo.getData().getRv3());
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setAdapter(imageAdapter3);

        ImageAdapter imageAdapter4 = new ImageAdapter(tabTwo.getData().getRv4());
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView4.setLayoutManager(layoutManager4);
        recyclerView4.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setAdapter(imageAdapter4);
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
        imageView.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
