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

import org.app.habib.ghiblifilms.R;
import org.app.habib.ghiblifilms.model.Character;
import org.app.habib.ghiblifilms.view.activity.DetailsActivity;
import org.app.habib.ghiblifilms.view.fragment.CharacterDetailsFragment;

import java.util.List;



public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.MyViewHolder> {

    private List<Character> characters;
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

    public CharactersAdapter(List<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;
        if(!characters.isEmpty()) {
            setDefaultCharacterDetails(characters.get(0));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_character, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Character character = characters.get(position);

        holder.name.setText(character.getName());

        Glide.with(context).load(character.getThumbnail(context)).centerCrop().into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails(character);
            }
        });
    }

    private void updateDetails(Character character) {
        CharacterDetailsFragment detailsFrag = (CharacterDetailsFragment)
                ((AppCompatActivity)context).getSupportFragmentManager().
                        findFragmentById(R.id.details_fragment);

        if (detailsFrag != null) {
            detailsFrag.updateDetailsView(character);
        } else {
            Intent nextActivity = new Intent(context, DetailsActivity.class);
            nextActivity.putExtra("character", character);
            context.startActivity(nextActivity);
        }
    }

    private void setDefaultCharacterDetails(Character character) {
        CharacterDetailsFragment detailsFrag = (CharacterDetailsFragment)
                ((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.details_fragment);

        if (detailsFrag != null) {
            detailsFrag.updateDetailsView(character);
        }
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
