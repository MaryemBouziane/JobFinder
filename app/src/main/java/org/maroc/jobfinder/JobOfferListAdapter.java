package org.maroc.jobfinder;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.maroc.jobfinder.R;
import org.maroc.jobfinder.models.JobOffer;

import java.util.List;


public class JobOfferListAdapter
        extends RecyclerView.Adapter<JobOfferListAdapter.ViewHolder> {
    private int jobOfferItemLayout;
    private List<JobOffer> jobOfferList;
    private JobOffer selectedJobOffer;

    public JobOfferListAdapter(int layoutId) {
        jobOfferItemLayout = layoutId;
    }

    public void setJobOfferList(List<JobOffer> jobOffers) {
        jobOfferList = jobOffers;
        notifyDataSetChanged();
    }

    public JobOffer getSelectedJobOffer() {
        return selectedJobOffer;
    }

    @Override
    public int getItemCount() {
        return jobOfferList == null ? 0 : jobOfferList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(jobOfferItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobOffer jobOffer = jobOfferList.get(position);
        holder.bind(jobOffer);

        // Ajoutez un OnClickListener à l'élément
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un Intent pour lancer l'activité de détails de l'offre d'emploi
                Intent intent = new Intent(v.getContext(), FavoriOfferDetailsActivity.class);

                // Ajoutez l'ID de l'offre d'emploi comme extra à l'Intent
                intent.putExtra("JOB_OFFER_ID", jobOffer.getId());

                // Lancez l'activité
                v.getContext().startActivity(intent);
            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView titre;
        TextView entrepriseNom;
        TextView typecontra;
        TextView nbrePlace;
        ViewHolder(View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.job_offer_row);
            entrepriseNom = itemView.findViewById(R.id.entrepriseNom);
            typecontra = itemView.findViewById(R.id.typeContrat);
            nbrePlace = itemView.findViewById(R.id.nombrePostes);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(JobOffer jobOffer) {
            titre.setText(jobOffer.getTitle());
            entrepriseNom.setText(jobOffer.getEntrepriseNom());
            typecontra.setText("type de contrat:"+jobOffer.getTypeContrat());
            nbrePlace.setText("place diponible:"+Integer.toString(jobOffer.getNombrePostes()));
            selectedJobOffer = jobOffer;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu);
        }
    }
}