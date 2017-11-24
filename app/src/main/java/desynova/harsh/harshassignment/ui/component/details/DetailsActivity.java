package desynova.harsh.harshassignment.ui.component.details;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.ui.base.BaseActivity;
import desynova.harsh.harshassignment.utils.Constants;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;

public class DetailsActivity extends BaseActivity
        implements DetailsContract.View {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    @Inject
    DetailsPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_toolbar_title)
    TextView txtToolbarTitle;
    @BindView(R.id.root_layout)
    View rootLayout;
    @BindView(R.id.gridLayout)
    LinearLayout gridLayout;
    @BindView(R.id.detailsLayout)
    LinearLayout detailsLayout;
    @BindView(R.id.detailsImage)
    AppCompatImageView detailsImageView;
    @BindView(R.id.detailsText)
    AppCompatTextView detailsText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    String title = DetailsActivity.class.getSimpleName();
    private int revealX;
    private int revealY;

    @Override
    protected void initializeDagger() {
        DesynovaApp app = (DesynovaApp) getApplicationContext();
        app.getMainComponent().inject(DetailsActivity.this);

    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected void initializeCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();

        if (getIntent() != null) {
            String type = getIntent().getStringExtra(Constants.TYPE);

            if (type.equals(Constants.GALLERY)) {
                gridLayout.setVisibility(View.VISIBLE);
                detailsLayout.setVisibility(View.GONE);
                List<TabOne.Datum> tabTwo = getIntent().getParcelableArrayListExtra(Constants.List);
                DetailsImageAdapter detailsImageAdapter = new DetailsImageAdapter(this, tabTwo);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.addItemDecoration(new SpacesItemDecorationGrid(this, 5, 2));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(detailsImageAdapter);

            } else if (type.equals(Constants.DETAILS)) {
                gridLayout.setVisibility(View.GONE);
                detailsLayout.setVisibility(View.VISIBLE);

                String text = getIntent().getStringExtra(Constants.Text);
                String url = getIntent().getStringExtra(Constants.Url);
                title = getIntent().getStringExtra(Constants.Title);
                Glide.with(detailsImageView.getContext())
                        .load(url)
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(detailsImageView);

                detailsText.setText(text);
                txtToolbarTitle.setText(title);
            }

        }

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);


            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
    }


    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(500);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
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

    @Override
    public void startActivity() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        txtToolbarTitle.setText(title);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
