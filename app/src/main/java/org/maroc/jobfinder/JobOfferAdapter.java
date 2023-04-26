package org.maroc.jobfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.JobOfferViewHolder> {

    private List<JobOffer> jobOffers;

    public JobOfferAdapter(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    @NonNull
    @Override
    public JobOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_offer_item, parent, false);
        return new JobOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder, int position) {
        JobOffer jobOffer = jobOffers.get(position);
        holder.title.setText(jobOffer.getTitle());
        holder.companyName.setText(jobOffer.getCompanyName());
        Picasso.get().load(jobOffer.getLogoUrl()).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return jobOffers.size();
    }

    public void updateData(List<JobOffer> newJobOffers) {
        this.jobOffers = newJobOffers;
        notifyDataSetChanged();
    }

    class JobOfferViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView companyName;
        ImageView logo;

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.jobTitle);
            companyName = itemView.findViewById(R.id.additionalInfo);
            logo = itemView.findViewById(R.id.companyLogo);
        }
    }
}

