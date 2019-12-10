package org.app.habib.ghiblifilms.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.app.habib.ghiblifilms.di.App;
import org.app.habib.ghiblifilms.rest.NetworkApi;
import org.app.habib.ghiblifilms.view.util.ViewListFragment;
import org.app.habib.ghiblifilms.R;
import org.app.habib.ghiblifilms.contract.LocationContract;
import org.app.habib.ghiblifilms.model.Location;
import org.app.habib.ghiblifilms.presenter.LocationPresenter;
import org.app.habib.ghiblifilms.view.adapter.LocationsAdapter;

import java.util.List;

import javax.inject.Inject;


public class LocationListFragmentList extends ViewListFragment implements LocationContract.View {

    private static final String TAG = LocationListFragmentList.class.getSimpleName();

    private RecyclerView recyclerView;
    private LocationPresenter presenter;

    @Inject
    NetworkApi api;

    public LocationListFragmentList() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((App) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        viewMain = view.findViewById(R.id.fragment_list);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        requestPresenterGetData();
    }

    public void requestPresenterGetData() {
        presenter = new LocationPresenter(this, api);
        presenter.loadData();
    }

    @Override
    public void onDataStarted() {
        progressShow();
    }

    @Override
    public void onDataCompleted() {
        progressHide();
    }

    @Override
    public void showData(List<Location> locations) {


        LocationsAdapter adapter = new LocationsAdapter(locations, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDataError(Throwable e) {
        Log.e(TAG, e.getMessage());
        progressHide();
        responseError(getString(R.string.http_error));
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }
}
