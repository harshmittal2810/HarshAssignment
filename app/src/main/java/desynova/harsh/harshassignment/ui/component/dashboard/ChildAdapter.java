package desynova.harsh.harshassignment.ui.component.dashboard;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;

public class ChildAdapter extends RecyclerView.Adapter<ViewHolderRecyclerView> {

    TabTwo tabTwo;
    Context mContext;
    ChildItemAdapter.OnViewMoreClickListner onViewMoreClickListner;

    ChildAdapter(Context context, TabTwo tabTwo, ChildItemAdapter.OnViewMoreClickListner onViewMoreClickListner) {
        this.tabTwo = tabTwo;
        this.onViewMoreClickListner = onViewMoreClickListner;
        mContext = context;
    }

    @Override
    public ViewHolderRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview, parent, false);
        return new ViewHolderRecyclerView(v);
    }


    @Override
    public void onBindViewHolder(ViewHolderRecyclerView holder, int position) {
        ChildItemAdapter childItemAdapter = null;
        switch (position) {
            case 0:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv1(), onViewMoreClickListner);
                break;

            case 1:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv2(), onViewMoreClickListner);

                break;

            case 2:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv3(), onViewMoreClickListner);

                break;

            case 3:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv4(), onViewMoreClickListner);

                break;

        }
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager1);
        holder.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        holder.recyclerView.setAdapter(childItemAdapter);
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}

