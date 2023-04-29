package org.maroc.jobfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.maroc.jobfinder.models.JobOffer;
import org.maroc.jobfinder.models.SelectedJobOffer;

public class JobOfferDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_JOB_OFFER = "extra_job_offer";

    private TextView titleTextView;
    private ImageView jobOfferLogo;
    private TextView jobOfferTitle;
    private TextView companyName;
    private TextView location;
    private TextView description;
    private TextView requirements;
    private TextView howToApply;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_job_offer_details);

            // Initialize views
            jobOfferLogo = findViewById(R.id.job_offer_details_logo);
            jobOfferTitle = findViewById(R.id.job_offer_details_title);
            companyName = findViewById(R.id.job_offer_details_company_name);

            // Récupérez l'objet JobOffer de l'intent
            JobOffer jobOffer = (JobOffer) getIntent().getSerializableExtra(EXTRA_JOB_OFFER);
            if (jobOffer != null) {
                displayJobOfferDetails(jobOffer);
            }
            //pour revenir à la page de laa recherche:
            FloatingActionButton backButton = findViewById(R.id.back_button);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }


    private void displayJobOfferDetails(JobOffer jobOffer) {
        // Load the company logo using Picasso
        Picasso.get()
                .load(jobOffer.getLogoUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(jobOfferLogo);

        // Set the text for the other views
        jobOfferTitle.setText(jobOffer.getTitle());
        companyName.setText(jobOffer.getDescription());
//je dois ajouter + de details +infos
    }
}
