package com.wallmart.busroute.ui.activities;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import com.wallmart.busroute.R;
import com.wallmart.busroute.constants.AppConstants;
import com.wallmart.busroute.database.column.BusRouteTableColumn;
import com.wallmart.busroute.database.table.DatabaseManager;
import com.wallmart.busroute.ui.adapters.StopsAdapter;
import com.wallmart.busroute.widgets.CustomTypeFacedTextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class RouteInfoActivity extends BaseActivity {

    @Bind(R.id.route_image)
    ImageView mRouteImage;

    @Bind(R.id.route_name)
    CustomTypeFacedTextView mRouteName;

    @Bind(R.id.route_description)
    CustomTypeFacedTextView mRouteDescription;

    @Bind(R.id.accessible_image)
    ImageView mAccessibleImage;

    @Bind(R.id.stops_list)
    RecyclerView mStopList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_activity);

        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        getSupportActionBar().setTitle(getResources().getString(R.string.route_information));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Fetch the bundle and display the data
        Bundle data = getIntent().getExtras();
        if (data != null) {
            String routeId = data.getString(AppConstants.TAG_ID);
            Cursor routeInfo = DatabaseManager.getInstance().fetchBusRouteInfo(this, routeId);
            if (routeInfo != null && routeInfo.getCount() > 0) {
                routeInfo.moveToFirst();
                String name = routeInfo.getString(routeInfo.getColumnIndex(BusRouteTableColumn.NAME));
                String description = routeInfo.getString(routeInfo.getColumnIndex(BusRouteTableColumn.DESCRIPTION));
                String image = routeInfo.getString(routeInfo.getColumnIndex(BusRouteTableColumn.IMAGE));
                String stops = routeInfo.getString(routeInfo.getColumnIndex(BusRouteTableColumn.STOP_NAME));
                String isAccessible = routeInfo.getString(routeInfo.getColumnIndex(BusRouteTableColumn.ACCESSIBLE));
                int visibility = (isAccessible.equalsIgnoreCase("true") ? View.VISIBLE : View.GONE);
                mAccessibleImage.setVisibility(visibility);

                mRouteName.setText(name);
                mRouteDescription.setText(description);

                Picasso.with(this)
                        .load(image)
                        .into(mRouteImage);

                String[] stopsArray = stops.split(",");
                mStopList.setHasFixedSize(true);
                mStopList.setItemAnimator(null);
                final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                mStopList.setLayoutManager(layoutManager);
                StopsAdapter stopsAdapter = new StopsAdapter(this, Arrays.asList(stopsArray));
                mStopList.setAdapter(stopsAdapter);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
