package desynova.harsh.harshassignment.ui.component.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.ui.base.BaseFragment;
import desynova.harsh.harshassignment.ui.base.listeners.RecyclerItemListener;
import desynova.harsh.harshassignment.ui.component.details.DetailsActivity;
import desynova.harsh.harshassignment.utils.Constants;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;
import io.huannguyen.swipeablerv.view.SWRecyclerView;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by harshmittal on 23/11/17.
 */

public class NotificationFragment extends BaseFragment implements NotificationContract.View, RecyclerItemListener {
    @Inject
    NotificationPresenter presenter;
    @BindView(R.id.recyclerView1)
    SWRecyclerView recyclerView1;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.rl_list)
    RelativeLayout rlList;
    Activity activity;
    List<TabThree.Datum> datum;

    @Override
    public void showMessage(String msg) {
        Snackbar.make(rlList, msg, LENGTH_SHORT).show();

    }

    @Override
    protected void initializeDagger() {
        DesynovaApp desynovaApp = (DesynovaApp) getActivity().getApplicationContext();
        desynovaApp.getMainComponent().inject(NotificationFragment.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    public void startActivity() {

    }

    @Override
    public void initializeList(TabThree tabThree) {
        if (isVisible() && isAdded()) {

            datum = tabThree.getData();
            NotificationAdapter notificationAdapter = new NotificationAdapter(tabThree.getData(), this);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

            recyclerView1.getSwipeMessageBuilder()
                    .withSwipeDirection(SWRecyclerView.SwipeMessageBuilder.BOTH)
                    .build();

            recyclerView1.setLayoutManager(layoutManager1);
            recyclerView1.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
            recyclerView1.setHasFixedSize(true);
            recyclerView1.setAdapter(notificationAdapter);

            recyclerView1.setupSwipeToDismiss(notificationAdapter, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        if (isVisible() && isAdded()) {
            rlList.setVisibility(isVisible ? VISIBLE : GONE);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onItemSelected(int position, String url) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, recyclerView1, "transition");
        int revealX = (int) (recyclerView1.getX() + recyclerView1.getWidth() / 2);
        int revealY = (int) (recyclerView1.getY() + recyclerView1.getHeight() / 2);

        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(Constants.TYPE, Constants.DETAILS);
        intent.putExtra(Constants.Url, datum.get(position).getUrl());
        intent.putExtra(Constants.Title, datum.get(position).getTitle());
        intent.putExtra(Constants.Text, datum.get(position).getText());
        intent.putExtra(DetailsActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(DetailsActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
