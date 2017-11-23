package desynova.harsh.harshassignment.ui.component.notification;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.ui.base.listeners.RecyclerItemListener;
import io.huannguyen.swipeablerv.adapter.StandardSWAdapter;

public class NotificationAdapter extends StandardSWAdapter<TabThree.Datum, NotificationViewHolder> {
    private List<TabThree.Datum> datumList;

    protected NotificationAdapter(List<TabThree.Datum> items) {
        super(items);
        datumList = items;
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bind(position, datumList.get(position));

    }


    @Override
    public int getItemCount() {
        return datumList.size();
    }

}

