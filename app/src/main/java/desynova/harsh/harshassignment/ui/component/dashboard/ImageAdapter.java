package desynova.harsh.harshassignment.ui.component.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    List<TabOne.Datum> datumList;

    ImageAdapter(List<TabOne.Datum> datumList) {
        this.datumList = datumList;
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

