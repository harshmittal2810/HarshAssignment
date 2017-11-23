package desynova.harsh.harshassignment.ui.component.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import desynova.harsh.harshassignment.R;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.utils.SpacesItemDecorationGrid;

/**
 * Created by harshmittal on 23/11/17.
 */

public class RecylerViewItem extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TabTwo tabTwo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recylerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void newInstance(TabTwo tabTwo) {
        this.tabTwo = tabTwo;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChildAdapter childAdapter = new ChildAdapter(getActivity(), tabTwo);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.addItemDecoration(new SpacesItemDecorationGrid(getActivity(), 5, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(childAdapter);
    }
}
