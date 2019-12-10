package org.app.habib.ghiblifilms.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.app.habib.ghiblifilms.model.Location;
import org.app.habib.ghiblifilms.view.fragment.LocationDetailsFragment;
import org.app.habib.ghiblifilms.R;
import org.app.habib.ghiblifilms.view.activity.DetailsActivity;

import java.util.Iterator;
import java.util.List;



public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.MyViewHolder> {

    private List<Location> locations;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View cardView;
        private ImageView thumbnail;
        private TextView name;
        MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            name = (TextView) itemView.findViewById(R.id.name);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }

    public LocationsAdapter(List<Location> locations, Context context) {
        this.locations = locations;
        this.context = context;

        for (Iterator<Location> i = locations.listIterator(); i.hasNext(); ) {
            Location location = i.next();
            int checkExistence = location.getPoster(context);
            if (checkExistence == 0 ) {
                i.remove();
            }
        }

        if(!locations.isEmpty()) {
            setDefaultCharacterDetails(locations.get(0));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_location, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Location location = locations.get(position);

        holder.name.setText(location.getName());

        Glide.with(context).load(location.getThumbnail(context)).centerCrop().into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails(location);
            }
        });
    }

    private void updateDetails(Location location) {
        LocationDetailsFragment detailsFrag = (LocationDetailsFragment)
                ((AppCompatActivity)context).getSupportFragmentManager().
                        findFragmentById(R.id.details_fragment);

        if (detailsFrag != null) {
            detailsFrag.updateDetailsView(location);
        } else {
            Intent nextActivity = new Intent(context, DetailsActivity.class);
            nextActivity.putExtra("location", location);
            context.startActivity(nextActivity);
        }
    }

    private void setDefaultCharacterDetails(Location location) {
        LocationDetailsFragment detailsFrag = (LocationDetailsFragment)
                ((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.details_fragment);

        if (detailsFrag != null) {
            detailsFrag.updateDetailsView(location);
        }
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
