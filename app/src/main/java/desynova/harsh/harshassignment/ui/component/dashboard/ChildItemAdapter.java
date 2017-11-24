package desynova.harsh.harshassignment.ui.component.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;

public class ChildItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM = 1;
    public static final int Footer = 2;
    List<TabOne.Datum> tabTwo;
    Context mContext;
    OnViewMoreClickListner onViewMoreClickListner;

    ChildItemAdapter(Context context, List<TabOne.Datum> datum, OnViewMoreClickListner onViewMoreClickListner) {
        this.tabTwo = datum;
        mContext = context;
        this.onViewMoreClickListner = onViewMoreClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Footer) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_item, parent, false);
            return new ViewHolderHeader(v);
        } else if (viewType == ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item, parent, false);
            return new ViewHolderChildItem(v);
        } else
            throw new RuntimeException("Could not inflate layout");
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderChildItem) {
            ViewHolderChildItem viewHolderChildItem = (ViewHolderChildItem) holder;
            Glide.with(viewHolderChildItem.imageView.getContext())
                    .load(tabTwo.get(position).getUrl())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(viewHolderChildItem.imageView);
        } else if (holder instanceof ViewHolderHeader) {
            ViewHolderHeader viewHolderHeader = (ViewHolderHeader) holder;
            viewHolderHeader.textView.setText("View More");
            viewHolderHeader.textView.setOnClickListener(view -> onViewMoreClickListner.onViewMore(tabTwo, viewHolderHeader.textView));
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        if (position > 1)
            return Footer;
        else
            return ITEM;

    }


    public interface OnViewMoreClickListner {
        void onViewMore(List<TabOne.Datum> tabTwo, View view);
    }


}

