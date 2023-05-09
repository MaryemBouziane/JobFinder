package org.maroc.jobfinder.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.maroc.jobfinder.R;


public class SettingsFragment extends Fragment {
    private EditText mCommuneEditText;
    private EditText mRadiusEditText;
    private EditText mTypeContratEditText;
    private EditText mNiveauFormationEditText;
    private EditText mSecteurActiviteEditText;
    private Button mValiderButton;

    public SettingsFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mCommuneEditText = view.findViewById(R.id.commune_edittext);
        mRadiusEditText = view.findViewById(R.id.radius_edittext);
        mTypeContratEditText = view.findViewById(R.id.type_contrat_edittext);
        mNiveauFormationEditText = view.findViewById(R.id.niveau_formation_edittext);
        mSecteurActiviteEditText = view.findViewById(R.id.secteur_activite_edittext);
        mValiderButton = view.findViewById(R.id.valider_button);
        mValiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs entrées par l'utilisateur
                String commune = mCommuneEditText.getText().toString();
                String radius = mRadiusEditText.getText().toString();
                String typeContrat = mTypeContratEditText.getText().toString();
                String niveauFormation = mNiveauFormationEditText.getText().toString();
                String secteurActivite = mSecteurActiviteEditText.getText().toString();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("recherche", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("commune", commune);
                editor.putString("radius", radius);
                editor.putString("typeContrat", typeContrat);
                editor.putString("niveauFormation", niveauFormation);
                editor.putString("secteurActivite", secteurActivite);
                editor.apply();

                // TODO : sauvegarder les valeurs dans les préférences partagées
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("recherche", Context.MODE_PRIVATE);
        mCommuneEditText.setText(sharedPreferences.getString("commune", ""));
        mRadiusEditText.setText(sharedPreferences.getString("radius", ""));
        mTypeContratEditText.setText(sharedPreferences.getString("typeContrat", ""));
        mNiveauFormationEditText.setText(sharedPreferences.getString("niveauFormation", ""));
        mSecteurActiviteEditText.setText(sharedPreferences.getString("secteurActivite", ""));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("nom_du_fichier", Context.MODE_PRIVATE);

        mCommuneEditText.setText(sharedPreferences.getString("commune", ""));
        mRadiusEditText.setText(sharedPreferences.getString("radius", ""));
        mTypeContratEditText.setText(sharedPreferences.getString("typeContrat", ""));
        mNiveauFormationEditText.setText(sharedPreferences.getString("niveauFormation", ""));
        mSecteurActiviteEditText.setText(sharedPreferences.getString("secteurActivite", ""));
    }
}
