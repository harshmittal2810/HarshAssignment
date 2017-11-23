package desynova.harsh.harshassignment.ui.component.dashboard;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import desynova.harsh.harshassignment.R;

/**
 * Created by harshmittal on 23/11/17.
 */

class ViewHolderHeader extends RecyclerView.ViewHolder {

    @BindView(R.id.textView)
    AppCompatTextView textView;

    public ViewHolderHeader(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
