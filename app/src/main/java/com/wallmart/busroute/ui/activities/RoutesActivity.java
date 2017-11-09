package com.wallmart.busroute.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.otto.Bus;

import com.wallmart.busroute.R;
import com.wallmart.busroute.Utils.OttoClient;
import com.wallmart.busroute.constants.AppConstants;
import com.wallmart.busroute.database.ProviderUri;
import com.wallmart.busroute.network.OnHttpResponseListener;
import com.wallmart.busroute.network.controllers.RouteController;
import com.wallmart.busroute.ui.adapters.RoutesAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class RoutesActivity extends BaseActivity implements View.OnClickListener, OnHttpResponseListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ARTICLES = 1;

    @Bind(R.id.routes_list)
    RecyclerView mRouteList;

    private Bus mBus;
    private RouteController mRouteController;
    private RoutesAdapter mRouteAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_activity);

        ButterKnife.bind(this);
        initViews();

        mBus = OttoClient.getInstance().getOttoBus();
        mRouteController = new RouteController();
        mRouteController.getListOfRoutes(this, this);
    }

    private void initViews() {
        getSupportActionBar().setTitle(getResources().getString(R.string.routes));

        mRouteList.setHasFixedSize(true);
        mRouteList.setItemAnimator(null);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRouteList.setLayoutManager(layoutManager);
        mRouteAdapter = new RoutesAdapter(this, null, this);
        mRouteList.setAdapter(mRouteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBus.register(this);
        getSupportLoaderManager().restartLoader(LOADER_ARTICLES, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(int requestCode, String message) {
        getSupportLoaderManager().restartLoader(LOADER_ARTICLES, null, this);
    }

    @Override
    public void onError(int statusCode, String message) {

    }

    @Override
    public void onError(String resStr) {

    }

    @Override
    public void onRequestCancelled(int requestCode) {

    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.card_view:
                Bundle data = new Bundle();
                String routeId = (String) view.getTag(R.id.tag_route_id);
                data.putString(AppConstants.TAG_ID, routeId);
                startActivity(new Intent(this, RouteInfoActivity.class).putExtras(data));
                break;

            default:
                break;
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_ARTICLES) {
            return new CursorLoader(this, ProviderUri.URI_BUS_ROUTES, null, null, null, null);

        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == LOADER_ARTICLES) {
            mRouteAdapter.swapCursor(data);

        }
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        mRouteAdapter.swapCursor(null);
    }


}
