package org.app.habib.ghiblifilms.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.app.habib.ghiblifilms.R;
import org.app.habib.ghiblifilms.model.Location;


public class LocationDetailsFragment extends Fragment {

    private TextView nameView;
    private TextView climateView;
    private TextView terrainView;
    private ImageView posterView;
    private TextView surfaceWaterView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_location, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        nameView = (TextView) view.findViewById(R.id.name);
        climateView = (TextView) view.findViewById(R.id.climate);
        terrainView = (TextView) view.findViewById(R.id.terrain);
        posterView = (ImageView) view.findViewById(R.id.poster);
        surfaceWaterView = (TextView) view.findViewById(R.id.surface_water);

        Bundle bundle = getArguments();
        if(bundle != null) {
            Location location = bundle.getParcelable("location");
            updateDetailsView(location);
        }
    }

    public void updateDetailsView(Location location) {
        if (location != null) {
            nameView.setText(location.getName());
            climateView.setText(location.getClimate());
            terrainView.setText(location.getTerrain());
            try {
                Glide.with(this).load(location.getPoster(getActivity())).centerCrop().into(posterView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            surfaceWaterView.setText(location.getSurfaceWater());
        }
    }
}
