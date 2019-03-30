package nistic.basicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context mContext;
    Float pixelDensity;

    private ArrayList<movieObject> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView movieTitle;
        TextView averageVote;
        TextView genre;
        TextView popularity;
        ImageView moviePoster;


        public MyViewHolder(View movieView) {
            super(movieView);
            this.card = movieView.findViewById(R.id.card);
            this.movieTitle = movieView.findViewById(R.id.movieTitle);
            this.averageVote = movieView.findViewById(R.id.averageVote);
            this.genre = movieView.findViewById(R.id.genre);
            this.popularity = movieView.findViewById(R.id.popularity);
            this.moviePoster = movieView.findViewById(R.id.moviePoster);
        }
    }

    public CustomAdapter(ArrayList<movieObject> data, Context context, Float pd) {
        this.dataSet = data;
        mContext = context;
        pixelDensity = pd;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        CardView card = holder.card;
        TextView movieTitle = holder.movieTitle;
        TextView averageVote = holder.averageVote;
        TextView genre = holder.genre;
        TextView popularity = holder.popularity;
        ImageView moviePoster = holder.moviePoster;

        String titleString = dataSet.get(listPosition).getMovieTitle();
        if (titleString.length() > 25) {
            movieTitle.setTextSize(22);
        } if (titleString.length() > 35) {
            movieTitle.setTextSize(18);
        } else if (titleString.length() < 25){
            movieTitle.setTextSize(28);
        }
        movieTitle.setText(titleString);
        averageVote.setText("Rating: \n" + dataSet.get(listPosition).getVoteAverage().toString());
        String genresString = "Genres: " + dataSet.get(listPosition).getGenres();
        if (genresString.length() > 25) {
            genre.setTextSize(12);
        } if (genresString.length() > 35) {
            genre.setTextSize(11);
        } else if (genresString.length() <= 25){
            genre.setTextSize(13);
        }

        if (pixelDensity < 2.5) {
            float pixelRatio = pixelDensity/2.5f;
//            moviePoster.setScaleX(pixelRatio);
//            moviePoster.setScaleY(pixelRatio);
            movieTitle.setTextSize(26 * pixelRatio);
            popularity.setTextSize(10);
            averageVote.setTextSize(10);
            genre.setTextSize(10);
        }
        genre.setText(genresString);
        popularity.setText("Popularity: " + dataSet.get(listPosition).getPopularity().toString() + "%");
        Picasso.get().load(dataSet.get(listPosition).getMoviePosterPath()).into(moviePoster);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, movieProfile.class);
                String[] allStrings = dataSet.get(listPosition).getAllStrings();
                allStrings[5] = dataSet.get(listPosition).getGenres();
                intent.putExtra("all_strings", allStrings);
                intent.putExtra("all_ints", dataSet.get(listPosition).getAllIntegers());
                intent.putExtra("all_doubles", new ArrayList<Double>((Arrays.asList(dataSet.get(listPosition).getAllDoubles()))));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
