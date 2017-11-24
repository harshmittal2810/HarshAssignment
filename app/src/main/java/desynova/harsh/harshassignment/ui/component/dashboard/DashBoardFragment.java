package desynova.harsh.harshassignment.ui.component.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.base.BaseFragment;
import desynova.harsh.harshassignment.ui.component.details.DetailsActivity;
import desynova.harsh.harshassignment.utils.Constants;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by harshmittal on 23/11/17.
 */

public class DashBoardFragment extends BaseFragment implements DashContract.View, ChildItemAdapter.OnViewMoreClickListner {
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

    Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
    }

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

            DashBoardAdapter dashBoardAdapter1 = new DashBoardAdapter(getActivity(), tabTwo, this);
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

    @Override
    public void onViewMore(List<TabOne.Datum> tabTwo, View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(Constants.TYPE, Constants.GALLERY);
        intent.putParcelableArrayListExtra(Constants.List, (ArrayList<? extends Parcelable>) tabTwo);
        intent.putExtra(DetailsActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(DetailsActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

}
