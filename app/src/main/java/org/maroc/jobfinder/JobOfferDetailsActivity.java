package org.maroc.jobfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.maroc.jobfinder.database.JobFinderDatabase;
import org.maroc.jobfinder.database.JobFinderRepository;
import org.maroc.jobfinder.models.JobOffer;

import java.util.List;

public class JobOfferDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_JOB_OFFER = "extra_job_offer";

    private TextView titleTextView;
    private ImageView jobOfferLogo;
    private TextView jobOfferTitle;
    private TextView companyName;
    private TextView date;
    private TextView description;
    private TextView requirements;
    private TextView typeContrat;

    private JobFinderDatabase db;

    // Dans la méthode onCreate:
    private JobFinderRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_details);
        repository = new JobFinderRepository(getApplication());

        // Initialize views
        jobOfferLogo = findViewById(R.id.job_offer_details_logo);
        jobOfferTitle = findViewById(R.id.job_offer_details_title);
        companyName = findViewById(R.id.job_offer_details_company_name);
        date= findViewById(R.id.job_offer_details_date);
        description = findViewById(R.id.job_offer_details_description);
        requirements = findViewById(R.id.job_offer_details_requirements);
        typeContrat = findViewById(R.id.job_offer_details_typeDeContrat);
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
        FloatingActionButton addToFavoritesButton = findViewById(R.id.add_to_favorites_button);
        addToFavoritesButton.setOnClickListener(v -> {
            addToFavorites(jobOffer);
            fetchAndPrintFavoriteJobOffers();
            Toast.makeText(this, "Job offer added to favorites", Toast.LENGTH_SHORT).show();
        });

    }

    private void addToFavorites(JobOffer jobOffer) {
        repository.insertJobOffer(jobOffer);
    }
    private void fetchAndPrintFavoriteJobOffers() {
        repository.getAllJobOffers().observe(this, new Observer<List<JobOffer>>() {
            @Override
            public void onChanged(List<JobOffer> favoriteJobOffers) {
                for (JobOffer jobOffer : favoriteJobOffers) {
                    Log.d("FAVORITE_JOBS", "Job ID: " + jobOffer.getId() + ", Title: " + jobOffer.getTitle());
                }
            }
        });
    }
    private void displayJobOfferDetails(JobOffer jobOffer) {
        // Load the company logo using Picasso
        Picasso.get()
                .load(jobOffer.getLogoURL())
                .placeholder(R.drawable.placeholder_image)
                .into(jobOfferLogo);

        // Set the text for the other views
        jobOfferTitle.setText(jobOffer.getTitle());
        companyName.setText(jobOffer.getEntrepriseNom());
        date.setText(jobOffer.getDateCreation());
        description.setText(jobOffer.getDescription());
        requirements.setText(jobOffer.getExperienceExige());
        typeContrat.setText(jobOffer.getTypeContrat());
//je dois ajouter + de details +infos
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.getSearchResults().removeObservers(this);
    }
}