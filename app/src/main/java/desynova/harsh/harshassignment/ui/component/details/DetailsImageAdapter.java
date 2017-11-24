package desynova.harsh.harshassignment.ui.component.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;

public class DetailsImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    Context mContext;
    private List<TabOne.Datum> datumList;

    DetailsImageAdapter(Context context, List<TabOne.Datum> datumList) {
        this.datumList = datumList;
        this.mContext = context;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(position, datumList.get(position));
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

}

