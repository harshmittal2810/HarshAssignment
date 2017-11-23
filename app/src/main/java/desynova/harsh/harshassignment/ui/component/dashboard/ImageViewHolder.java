package desynova.harsh.harshassignment.ui.component.dashboard;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_image)
    AppCompatImageView itemImage;
    @BindView(R.id.rl_image_item)
    RelativeLayout itemLayout;
    @BindView(R.id.cardView)
    CardView cardView;


    public ImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(int position, TabOne.Datum datum) {
        //need to move to mapper

        Glide.with(itemImage.getContext())
                .load(datum.getUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(itemImage);
    }

}

