package com.wallmart.busroute.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import com.wallmart.busroute.R;
import com.wallmart.busroute.database.column.BusRouteTableColumn;
import com.wallmart.busroute.widgets.CustomTypeFacedTextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class RoutesAdapter extends CursorRecyclerAdapter<RecyclerView.ViewHolder> {

    private static Context mContext;
    private final View.OnClickListener mOnClickListener;
    private Cursor mCursor;

    /**
     * @param context         context
     * @param cursor          cursor reference
     * @param onClickListener clickListener reference
     */
    public RoutesAdapter(final Context context, final Cursor cursor, final View.OnClickListener
            onClickListener) {
        super(cursor);
        mOnClickListener = onClickListener;
        mCursor = cursor;
        mContext = context;
        init(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_adapter, parent, false);
        RecyclerView.ViewHolder viewHolder = new BusRouteListViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final Cursor cursor) {
        if (holder instanceof BusRouteListViewHolder) {
            String routeName = cursor.getString(cursor.getColumnIndex(BusRouteTableColumn.NAME));
            String routeimage = cursor.getString(cursor.getColumnIndex(BusRouteTableColumn.IMAGE));
            String routeId =  cursor.getString(cursor.getColumnIndex(BusRouteTableColumn.ROUTE_ID));

            ((BusRouteListViewHolder) holder).mRouteName.setText(routeName);
            Picasso.with(mContext)
                    .load(routeimage)
                    .into(((BusRouteListViewHolder) holder).mRouteImage);

            ((BusRouteListViewHolder) holder).mCardView.setOnClickListener(mOnClickListener);
            ((BusRouteListViewHolder) holder).mCardView.setTag(R.id.tag_route_id,routeId);

            ((BusRouteListViewHolder) holder).mRouteDescription.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return position;
    }

    /**
     * @param context
     */
    private void init(final Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Cursor swapCursor(final Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
        mCursor = newCursor;
        notifyDataSetChanged();
        return super.swapCursor(newCursor);
    }

    public static class BusRouteListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_view)
        CardView mCardView;

        @Bind(R.id.route_image)
        ImageView mRouteImage;

        @Bind(R.id.route_name)
        CustomTypeFacedTextView mRouteName;

        @Bind(R.id.route_description)
        CustomTypeFacedTextView mRouteDescription;

        public BusRouteListViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
