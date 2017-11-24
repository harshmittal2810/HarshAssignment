package desynova.harsh.harshassignment.ui.component.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.ui.base.listeners.RecyclerItemListener;
import io.huannguyen.swipeablerv.adapter.StandardSWAdapter;

public class NotificationAdapter extends StandardSWAdapter<TabThree.Datum, NotificationViewHolder> {
    RecyclerItemListener recyclerItemListener;
    private List<TabThree.Datum> datumList;

    protected NotificationAdapter(List<TabThree.Datum> items, RecyclerItemListener recyclerItemListener) {
        super(items);
        datumList = items;
        this.recyclerItemListener = recyclerItemListener;
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bind(position, datumList.get(position));
        holder.cardView.setOnClickListener(view -> recyclerItemListener.onItemSelected(position, datumList.get(position).getUrl()));

    }


    @Override
    public int getItemCount() {
        return datumList.size();
    }

}

