package org.maroc.jobfinder;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maroc.jobfinder.database.JobFinderRepository;
import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.JobOfferViewHolder> {
    private JobFinderRepository repository;
    private List<JobOffer> jobOffers;
    private OnJobOfferClickListener onJobOfferClickListener;

    public JobOfferAdapter(List<JobOffer> jobOffers, OnJobOfferClickListener onJobOfferClickListener) {
        this.jobOffers = jobOffers;
        this.onJobOfferClickListener = onJobOfferClickListener;
    }


    public JobOfferAdapter(List<JobOffer> jobOffers, OnJobOfferClickListener onJobOfferClickListener, JobFinderRepository repository) {
        this.jobOffers = jobOffers;
        this.onJobOfferClickListener = onJobOfferClickListener;
        this.repository = repository;
    }
    @NonNull
    @Override
    public JobOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_offer_item, parent, false);
        return new JobOfferViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder, int position) {
        JobOffer jobOffer = jobOffers.get(position);
        holder.bind(jobOffer);

        ConstraintLayout constraintLayout = holder.itemView.findViewById(R.id.constraintLayout);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        if (position % 2 == 0) {
            // Photo à gauche et texte à droite pour les éléments pairs
            constraintSet.connect(R.id.companyLogo, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(R.id.companyLogo, ConstraintSet.END, R.id.jobTitle, ConstraintSet.START);
            constraintSet.connect(R.id.jobTitle, ConstraintSet.START, R.id.companyLogo, ConstraintSet.END);
        } else {
            // Photo à droite et texte à gauche pour les éléments impairs
            constraintSet.connect(R.id.companyLogo, ConstraintSet.START, R.id.jobTitle, ConstraintSet.END, 16); // Ajoutez une marge de 16dp
            constraintSet.connect(R.id.companyLogo, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            constraintSet.connect(R.id.jobTitle, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(R.id.jobTitle, ConstraintSet.END, R.id.companyLogo, ConstraintSet.START, 16); // Ajoutez une marge de 16dp
        }

        constraintSet.applyTo(constraintLayout);
    }

    @Override
    public int getItemCount() {
        return jobOffers.size();
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
        notifyDataSetChanged();
    }


    public class JobOfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        TextView title;
        TextView description;
        ImageView companyLogo;

        public void bind(JobOffer jobOffer) {
            title.setText(jobOffer.getTitle());
            description.setText(jobOffer.getDateCreation());

            String imageUrl = jobOffer.getLogoURL();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .into(companyLogo);
            } else {
                companyLogo.setImageResource(R.drawable.placeholder_image); // Remplacez 'placeholder_image' par le nom de l'image par défaut que vous souhaitez afficher
            }
        }

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobTitle);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            description = itemView.findViewById(R.id.additionalInfo);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this); // Enregistrement pour le menu contextuel
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 121, 0, "Ajouter aux favoris");
            menu.getItem(0).setOnMenuItemClickListener(item -> {
                if (onJobOfferClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onJobOfferClickListener.onAddToFavoritesClick(jobOffers.get(position));
                        return true;
                    }
                }
                return false;
            });
        }
        @Override
        public void onClick(View view) {
            if (onJobOfferClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onJobOfferClickListener.onJobOfferClick(jobOffers.get(position));
                }
            }
        }
    }

    public interface OnJobOfferClickListener {
        void onJobOfferClick(JobOffer jobOffer);
        void onAddToFavoritesClick(JobOffer jobOffer);
    }
}

