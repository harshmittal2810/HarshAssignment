package desynova.harsh.harshassignment.ui.component.dashboard;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.component.main.MainActivity;

public class DashBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER = 0;
    private static final int ITEM = 1;
    TabTwo tabTwo;
    Context mContext;

    DashBoardAdapter(Context context, TabTwo tabTwo) {
        this.tabTwo = tabTwo;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
            return new ViewHolderHeader(v);
        } else if (viewType == ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment, parent, false);
            return new ViewHolderItems(v);
        } else
            throw new RuntimeException("Could not inflate layout");
    }


    @Override
    public int getItemViewType(int position) {
        if (position == HEADER)
            return HEADER;
        else
            return ITEM;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderHeader) {
            ViewHolderHeader viewHolderHeader = (ViewHolderHeader) holder;
            viewHolderHeader.textView.setText(tabTwo.getData().getDescription());
        } else if (holder instanceof ViewHolderItems) {
            FragmentManager fragMan = ((MainActivity) mContext).getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragMan.beginTransaction();
            RecylerViewItem myFrag = new RecylerViewItem();
            myFrag.newInstance(tabTwo);
            fragTransaction.replace(R.id.fragment_child, myFrag);
            fragTransaction.commit();

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }


}

