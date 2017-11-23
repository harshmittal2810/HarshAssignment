package desynova.harsh.harshassignment.ui.component.home;

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
import desynova.harsh.harshassignment.ui.base.listeners.RecyclerItemListener;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    Listener mListener;
    Context mContext;
    RecyclerItemListener recyclerItemListener;
    private List<TabOne.Datum> datumList;

    ImageAdapter(Context context, List<TabOne.Datum> datumList, Listener listener, RecyclerItemListener recyclerItemListener) {
        this.datumList = datumList;
        this.mListener = listener;
        this.mContext = context;
        this.recyclerItemListener = recyclerItemListener;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    public DragListener getDragInstance() {
        if (mListener != null) {
            return new DragListener(mListener);
        } else {
            Log.e("Route Adapter: ", "Initialize listener first!");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(position, datumList.get(position));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerItemListener.onItemSelected(position, datumList.get(position).getUrl());
            }
        });

        holder.cardView.setTag(position);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        holder.cardView.setOnDragListener(new DragListener(mListener));
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }


    public List<TabOne.Datum> getDatumList() {
        return datumList;
    }

    public void updateList(List<TabOne.Datum> datumList) {
        this.datumList = datumList;
    }


    public interface Listener {
        void setEmptyList(boolean visibility);
    }

    public class DragListener implements View.OnDragListener {

        boolean isDropped = false;
        Listener mListener;

        public DragListener(Listener listener) {
            this.mListener = listener;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundColor(Color.LTGRAY);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundColor(Color.YELLOW);
                    break;

                case DragEvent.ACTION_DROP:

                    isDropped = true;
                    int positionSource = -1;
                    int positionTarget = -1;

                    View viewSource = (View) event.getLocalState();

                    if (v.getId() == R.id.cardView || v.getId() == R.id.textEmptyList) {
                        //RecyclerView target = (RecyclerView) v.getParent();
                        RecyclerView target;
                        if (v.getId() == R.id.textEmptyList) {
                            target = (RecyclerView)
                                    v.getRootView().findViewById(R.id.recyclerView2);
                        } else {
                            target = (RecyclerView) v.getParent();
                            positionTarget = (int) v.getTag();
                        }

                        RecyclerView source = (RecyclerView) viewSource.getParent();

                        ImageAdapter adapterSource = (ImageAdapter) source.getAdapter();
                        positionSource = (int) viewSource.getTag();

                        TabOne.Datum datum = (TabOne.Datum) adapterSource.getDatumList().get(positionSource);
                        List<TabOne.Datum> customListSource = adapterSource.getDatumList();

                        customListSource.remove(positionSource);
                        adapterSource.updateList(customListSource);
                        adapterSource.notifyDataSetChanged();

                        ImageAdapter adapterTarget = (ImageAdapter) target.getAdapter();
                        List<TabOne.Datum> customListTarget = adapterTarget.getDatumList();
                        if (positionTarget >= 0) {
                            customListTarget.add(positionTarget, datum);
                        } else {
                            customListTarget.add(datum);
                        }
                        adapterTarget.updateList(customListTarget);
                        adapterTarget.notifyDataSetChanged();
                        v.setVisibility(View.VISIBLE);

                        if (source.getId() == R.id.recyclerView2
                                && adapterSource.getItemCount() < 1) {
                            mListener.setEmptyList(true);
                        }

                        if (v.getId() == R.id.textEmptyList) {
                            mListener.setEmptyList(false);
                        }
                    }

                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundColor(0);
                    break;

                default:
                    break;
            }

            if (!isDropped) {
                View vw = (View) event.getLocalState();
                vw.setVisibility(View.VISIBLE);
            }

            return true;
        }

    }
}

