package desynova.harsh.harshassignment.ui.component.dashboard;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import desynova.harsh.harshassignment.R;

/**
 * Created by harshmittal on 23/11/17.
 */

class ViewHolderChildItem extends RecyclerView.ViewHolder {

    @BindView(R.id.image_view)
    AppCompatImageView imageView;

    public ViewHolderChildItem(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
