package org.app.habib.ghiblifilms.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.app.habib.ghiblifilms.model.Character;
import org.app.habib.ghiblifilms.R;



public class CharacterDetailsFragment extends Fragment {

    private TextView nameView;
    private TextView genderView;
    private TextView ageView;
    private ImageView posterView;
    private TextView eyeColorView;
    private TextView hairColorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_character, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        nameView = (TextView) view.findViewById(R.id.name);
        genderView = (TextView) view.findViewById(R.id.gender);
        ageView = (TextView) view.findViewById(R.id.age);
        posterView = (ImageView) view.findViewById(R.id.poster);
        eyeColorView = (TextView) view.findViewById(R.id.eye_color);
        hairColorView = (TextView) view.findViewById(R.id.hair_color);

        Bundle bundle = getArguments();
        if(bundle != null) {
            Character character = bundle.getParcelable("character");
            updateDetailsView(character);
        }
    }

    public void updateDetailsView(Character character) {
        if (character != null) {
            nameView.setText(character.getName());
            String gender = character.getGender();
            if (gender.trim().equals("")) {
                gender = getString(R.string.unknown);
            }
            genderView.setText(gender);
            String age = character.getAge();
            if (age.trim().equals("")) {
                age = getString(R.string.unknown);
            }
            ageView.setText(age);
            try {
                Glide.with(this).load(character.getPoster(getActivity())).fitCenter().into(posterView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            eyeColorView.setText(character.getEyeColor());
            hairColorView.setText(character.getHairColor());
        }
    }
}
