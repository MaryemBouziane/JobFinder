package org.maroc.jobfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.JobOfferViewHolder> {

    private List<JobOffer> jobOffers;

    public JobOfferAdapter(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    @NonNull
    @Override
    public JobOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_offer_item, parent, false);
        return new JobOfferViewHolder(itemView);
    }
    ImageView companyLogo;
    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder, int position) {
        JobOffer jobOffer = jobOffers.get(position);
        holder.title.setText(jobOffer.getTitle());
        holder.description.setText(jobOffer.getDescription());
        //  Picasso pour charger l'image à partir de l'URL
        String imageUrl = jobOffer.getLogoUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
       .into(holder.companyLogo);
        } else {
            holder.companyLogo.setImageResource(R.drawable.placeholder_image); // Remplacez 'placeholder_image' par le nom de l'image par défaut que vous souhaitez afficher
        }
    }

    @Override
    public int getItemCount() {
        return jobOffers.size();
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
        notifyDataSetChanged();
    }

    public static class JobOfferViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView companyLogo;

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobTitle);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            description = itemView.findViewById(R.id.additionalInfo);
        }
    }
}
