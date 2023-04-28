package org.maroc.jobfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder, int position) {
        JobOffer jobOffer = jobOffers.get(position);
        holder.title.setText(jobOffer.getTitle());
        holder.company.setText(jobOffer.getId());
        holder.location.setText(jobOffer.getDescription());
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
        TextView company;
        TextView location;

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobTitle);
            company = itemView.findViewById(R.id.companyLogo);
            location = itemView.findViewById(R.id.additionalInfo);
        }
    }
}
