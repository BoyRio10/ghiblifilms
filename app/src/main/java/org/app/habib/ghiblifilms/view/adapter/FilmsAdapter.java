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

import org.app.habib.ghiblifilms.model.Film;
import org.app.habib.ghiblifilms.view.activity.DetailsActivity;
import org.app.habib.ghiblifilms.view.fragment.FilmDetailsFragment;
import org.app.habib.ghiblifilms.R;

import java.util.List;



public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.MyViewHolder> {

    private List<Film> films;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View cardView;
        private ImageView thumbnail;
        private TextView title, releaseDate;
        MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            releaseDate = (TextView) itemView.findViewById(R.id.release_date);
        }
    }

    public FilmsAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
        if(!films.isEmpty()) {
            setDefaultFilmDetails(films.get(0));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_film, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Film film = films.get(position);

        holder.title.setText(film.getTitle());
        holder.releaseDate.setText(film.getReleaseDate());

        Glide.with(context).load(film.getThumbnail(context)).centerCrop().into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails(film);
            }
        });
    }

    private void updateDetails(Film film) {
        FilmDetailsFragment detailsFrag = (FilmDetailsFragment)
                ((AppCompatActivity)context).getSupportFragmentManager().
                        findFragmentById(R.id.details_fragment);

        if (detailsFrag != null) {
            detailsFrag.updateDetailsView(film);
        } else {
            Intent nextActivity = new Intent(context, DetailsActivity.class);
            nextActivity.putExtra("film", film);
            context.startActivity(nextActivity);
        }
    }

    private void setDefaultFilmDetails(Film film) {
        FilmDetailsFragment detailsFrag = (FilmDetailsFragment)
                ((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.details_fragment);

        if (detailsFrag != null) {
            detailsFrag.updateDetailsView(film);
        }
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
}
