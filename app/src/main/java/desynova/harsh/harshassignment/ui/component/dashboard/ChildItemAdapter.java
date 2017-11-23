package desynova.harsh.harshassignment.ui.component.dashboard;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;

public class ChildItemAdapter extends RecyclerView.Adapter<ViewHolderChildItem> {

    List<TabOne.Datum> tabTwo;
    Activity mContext;

    ChildItemAdapter(Activity context, List<TabOne.Datum> datum) {
        this.tabTwo = datum;
        mContext = context;
    }

    @Override
    public ViewHolderChildItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item, parent, false);
        return new ViewHolderChildItem(v);
    }


    @Override
    public void onBindViewHolder(ViewHolderChildItem holder, int position) {


        Glide.with(holder.imageView.getContext())
                .load(tabTwo.get(position).getUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 4;
    }


}

