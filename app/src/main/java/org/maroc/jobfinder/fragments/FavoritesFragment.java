package org.maroc.jobfinder.fragments;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maroc.jobfinder.FavoritesViewModel;
import org.maroc.jobfinder.JobOfferListAdapter;
import org.maroc.jobfinder.R;
import org.maroc.jobfinder.models.JobOffer;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {
    private JobOfferListAdapter adapter;
    private FavoritesViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        observerSetup();
        recyclerSetup();
        Button filterButton = view.findViewById(R.id.filter_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.filter_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.contract_type:
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Select contract type");

                                final EditText input = new EditText(getContext());
                                builder.setView(input);

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selectedContractType = input.getText().toString();
                                        viewModel.findJobOffersByContractType(selectedContractType).observe(getViewLifecycleOwner(), new Observer<List<JobOffer>>() {
                                            @Override
                                            public void onChanged(List<JobOffer> jobOffers) {
                                                // Update your RecyclerView with the filtered job offers
                                                adapter.setJobOfferList(jobOffers);
                                            }
                                        });
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                builder.show();
                                return true;
                            case R.id.min_postes:
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                                builder2.setTitle("Select contract type");

                                final EditText input2 = new EditText(getContext());
                                builder2.setView(input2);

                                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selectedminposte = input2.getText().toString();
                                        viewModel.findJobOffersWithMinPostes(Integer.parseInt(selectedminposte)).observe(getViewLifecycleOwner(), new Observer<List<JobOffer>>() {
                                            @Override
                                            public void onChanged(List<JobOffer> jobOffers) {
                                                // Update your RecyclerView with the filtered job offers
                                                adapter.setJobOfferList(jobOffers);
                                            }
                                        });
                                    }
                                });
                                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                builder2.show();
                                return true;
                            case R.id.lacking_candidates:
                                AlertDialog.Builder builder3 = new AlertDialog.Builder(getContext());
                                builder3.setTitle("Select contract type");
                                builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        viewModel.findJobOffersLackingCandidates().observe(getViewLifecycleOwner(), new Observer<List<JobOffer>>() {
                                            @Override
                                            public void onChanged(List<JobOffer> jobOffers) {
                                                // Update your RecyclerView with the filtered job offers
                                                adapter.setJobOfferList(jobOffers);
                                            }
                                        });
                                    }
                                });
                                builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                builder3.show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });
    }

    private void observerSetup() {

        Log.d("FavoritesFragment", "observerSetup called");
        viewModel.getAllFavoriteJobOffers().observe(getViewLifecycleOwner(),
                new Observer<List<JobOffer>>() {
                    @Override
                    public void onChanged(@Nullable final List<JobOffer> jobOffers) {
                        adapter.setJobOfferList(jobOffers);

                        Log.d("FavoritesFragment", "Data changed, updating adapter");
                        Log.d("FavoritesFragment", "Number of job offers: " + jobOffers.size());
                      }
                });
    }


    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new JobOfferListAdapter(R.layout.job_offer_list_item);
        recyclerView = getView().findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        recyclerView.setOnCreateContextMenuListener(new RecyclerView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuInflater inflater = getActivity().getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);

                menu.findItem(R.id.delete_favorite).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Récupérez l'offre d'emploi actuellement sélectionnée
                        JobOffer selectedJobOffer = adapter.getSelectedJobOffer();

                        if (selectedJobOffer != null) {
                            // Supprimez l'offre d'emploi de la liste des favoris
                            viewModel.deleteJobOffer(selectedJobOffer.getId());

                            // Mettez à jour l'adapter
                            adapter.notifyDataSetChanged();
                        }

                        return true;
                    }
                });

                menu.findItem(R.id.copy_link).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Récupérez l'offre d'emploi sélectionnée
                        JobOffer selectedJobOffer = adapter.getSelectedJobOffer();

                        // Récupérez l'URL de l'offre d'emploi (vous devrez peut-être modifier cette ligne en fonction de la façon dont vous stockez l'URL dans l'objet JobOffer)
                        String jobOfferUrl = selectedJobOffer.getOffreURL();

                        // Obtenez le service de presse-papier
                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

                        // Créez un ClipData
                        ClipData clip = ClipData.newPlainText("Lien de l'offre d'emploi", jobOfferUrl);

                        // Copiez le lien dans le presse-papier
                        clipboard.setPrimaryClip(clip);

                        // Informez l'utilisateur que le lien a été copié
                        Toast.makeText(getActivity(), "Lien de l'offre d'emploi copié", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });

            }
        });
    }

}