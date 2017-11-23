package desynova.harsh.harshassignment.ui.component.notification;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_image)
    AppCompatImageView itemImage;
    @BindView(R.id.title)
    AppCompatTextView title;
    @BindView(R.id.description)
    AppCompatTextView description;
    @BindView(R.id.cardView)
    CardView cardView;


    public NotificationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(int position, TabThree.Datum datum) {
        //need to move to mapper

        title.setText(datum.getTitle());
        description.setText(datum.getText());
        Glide.with(itemImage.getContext())
                .load(datum.getUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(itemImage);
    }

}

