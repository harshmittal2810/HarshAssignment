package desynova.harsh.harshassignment.ui.component.dashboard;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;

public class ChildAdapter extends RecyclerView.Adapter<ViewHolderRecylerView> {

    TabTwo tabTwo;
    Activity mContext;

    ChildAdapter(Activity context, TabTwo tabTwo) {
        this.tabTwo = tabTwo;
        mContext = context;
    }

    @Override
    public ViewHolderRecylerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview, parent, false);
        return new ViewHolderRecylerView(v);
    }


    @Override
    public void onBindViewHolder(ViewHolderRecylerView holder, int position) {
        ChildItemAdapter childItemAdapter = null;
        switch (position) {
            case 0:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv1());
                break;

            case 1:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv2());

                break;

            case 2:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv3());

                break;

            case 3:
                childItemAdapter = new ChildItemAdapter(mContext, tabTwo.getData().getRv4());

                break;

        }
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager1);
        holder.recyclerView.addItemDecoration(new SpacesItemDecorationGrid(mContext, 5, 2));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(childItemAdapter);
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}

