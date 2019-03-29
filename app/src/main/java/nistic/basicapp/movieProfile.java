package nistic.basicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class movieProfile extends AppCompatActivity {

    ImageView backdrop;
    ImageView poster;

    TextView title;
    TextView genres;
    TextView overview;
    TextView ratingVoteCount;
    TextView popularity;
    TextView adult;
    TextView onVideo;
    TextView dateRelease;
    TextView originalTitle;
    TextView originalLanguage;

    String[] allStrings;
    ArrayList<Integer> allIntegers;
    ArrayList<Double> allDoubles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_profile);

        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);

        title = findViewById(R.id.title);
        genres = findViewById(R.id.genres);
        overview = findViewById(R.id.overview);
        ratingVoteCount = findViewById(R.id.ratingVoteCount);
        popularity = findViewById(R.id.popularity);
        adult = findViewById(R.id.adult);
        onVideo = findViewById(R.id.isVideo);
        dateRelease = findViewById(R.id.releaseDate);
        originalLanguage = findViewById(R.id.originalLanguage);
        originalTitle = findViewById(R.id.originalTitle);

        //   unpack the extra and set all the elements to their respective data
        getExtras();

        title.setText(allStrings[0]);
        onVideo.setText("Out on Video: " + allStrings[1]);

        Picasso.get().load(allStrings[2]).into(poster);

        originalLanguage.setText("Original Language: " + allStrings[3]);
        originalTitle.setText("Original Title: " + allStrings[4]);
        genres.setText("Genres: " + allStrings[5]);

        Picasso.get().load(allStrings[6]).into(backdrop);

        adult.setText("Adult: " + allStrings[7]);
        overview.setText(allStrings[8]);
        dateRelease.setText("Release Date: " + allStrings[9]);

        ratingVoteCount.setText("Rating: " + allDoubles.get(0) + "    Vote total: " + allIntegers.get(0));
        popularity.setText("Popularity: " + allDoubles.get(1) + "%");



    }

    public void getExtras() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            allStrings = extras.getStringArray("all_strings");
            allIntegers = extras.getIntegerArrayList("all_ints");
            allDoubles = (ArrayList<Double>) getIntent().getSerializableExtra("all_doubles");
        }
    }

}
