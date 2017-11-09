package com.wallmart.busroute.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import com.wallmart.busroute.R;
import com.wallmart.busroute.widgets.CustomTypeFacedTextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.StopsViewHolder> {
    private Context mContext;
    private List<String> mStopsList;

    public StopsAdapter(final Context context, final List<String> stopsList) {
        this.mContext = context;
        this.mStopsList = stopsList;
    }

    @Override
    public StopsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stop_layout, parent, false);

        return new StopsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StopsViewHolder holder, int position) {
        String movie = mStopsList.get(position);
        holder.mStopName.setText(movie);

        int iconId = ((position == mStopsList.size() - 1)) ? R.drawable.circle
                : R.drawable.route_stop_flow;
        holder.mStopIcon.setImageDrawable(ContextCompat.getDrawable(mContext, iconId));
    }

    @Override
    public int getItemCount() {
        return mStopsList.size();
    }

    class StopsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.stop_icon)
        ImageView mStopIcon;

        @Bind(R.id.stop_name)
        CustomTypeFacedTextView mStopName;

        public StopsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
