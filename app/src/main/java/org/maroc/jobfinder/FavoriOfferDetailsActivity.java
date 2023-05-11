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

public class FavoriOfferDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_JOB_OFFER = "extra_job_offer";
    private JobOffer jobOffer;
    private TextView titleTextView;
    private ImageView jobOfferLogo;
    private TextView jobOfferTitle;
    private TextView companyName;
    private TextView location;
    private TextView description;
    private TextView requirements;
    private TextView howToApply;

    private JobFinderDatabase db;
    private boolean isFavorite;
    private FloatingActionButton addToFavoritesButton;

    private JobFinderRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_details);
        repository = new JobFinderRepository(getApplication());

        // Initialiser les vues
        jobOfferLogo = findViewById(R.id.job_offer_details_logo);
        jobOfferTitle = findViewById(R.id.job_offer_details_title);
        companyName = findViewById(R.id.job_offer_details_company_name);

        // Récupérez l'identifiant du JobOffer de l'intent
        String jobOfferId = getIntent().getStringExtra("JOB_OFFER_ID");

        // Utilisez l'identifiant pour obtenir l'objet JobOffer de votre base de données
        repository.getJobOfferById(jobOfferId).observe(this, foundJobOffer -> {
            if (foundJobOffer != null) {
                this.jobOffer = foundJobOffer;  // Initialisez la variable d'instance jobOffer
                displayJobOfferDetails(foundJobOffer);
            }
        });


        // pour revenir à la page de la recherche:
        FloatingActionButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> onBackPressed());

        addToFavoritesButton = findViewById(R.id.add_to_favorites_button);
        addToFavoritesButton.setOnClickListener(v -> {
            if (jobOffer == null) {
                Toast.makeText(this, "Job offer not loaded yet", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isFavorite) {
                addToFavorites(jobOffer);
                fetchAndPrintFavoriteJobOffers();
                Toast.makeText(this, "Job offer added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                removeFromFavorites(jobOffer);
                Toast.makeText(this, "Job offer removed from favorites", Toast.LENGTH_SHORT).show();
            }
            isFavorite = !isFavorite;
            updateFavoritesButton();
        });

        // Check if job offer is already a favorite
        repository.getJobOfferById(jobOfferId).observe(this, foundJobOffer -> {
            isFavorite = foundJobOffer != null;
            updateFavoritesButton();
        });

    }

    private void updateFavoritesButton() {
        // Change the button icon or text based on isFavorite
        if (isFavorite) {
            addToFavoritesButton.setImageResource(R.drawable.ic_delete);  // icon for favorite
        } else {
            addToFavoritesButton.setImageResource(R.drawable.ic_favorite_border);  // icon for not favorite
        }
    }

    private void addToFavorites(JobOffer jobOffer) {
        repository.insertJobOffer(jobOffer);
    }

    private void removeFromFavorites(JobOffer jobOffer) {
        repository.deleteJobOffer(jobOffer.getId());
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
        companyName.setText(jobOffer.getDescription());
        // Ajoutez plus de détails et d'informations ici
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.getSearchResults().removeObservers(this);
    }
}