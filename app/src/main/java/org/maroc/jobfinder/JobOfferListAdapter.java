package org.maroc.jobfinder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
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

    public JobOfferListAdapter(int layoutId) {
        jobOfferItemLayout = layoutId;
    }

    public void setJobOfferList(List<JobOffer> jobOffers) {
        jobOfferList = jobOffers;
        notifyDataSetChanged();
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
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(jobOfferList.get(listPosition).getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.job_offer_row);
        }
    }
}