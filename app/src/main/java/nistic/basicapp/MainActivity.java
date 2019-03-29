package nistic.basicapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    JsonArray rootArray;
    JsonArray genreArray;
    TextView pageCountElement;
    TextView page1;
    TextView page2;
    TextView page3;
    TextView page4;
    TextView page5;
    TextView page6;

    private float pixelDensity = 0.0f;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    Integer pageCount = 1;
    Integer maxPages = 0;
    Integer resultsSize = 0;
    Object[][] genresArray;

    Button nextPage;
    Button previousPage;
    SearchView s;

    ArrayList<movieObject> movieArray;
    ArrayList<String> movieTitles;
    ArrayList<String> movieDates;
    Spinner sorter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pixelDensity = getResources().getDisplayMetrics().density;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = (RecyclerView) findViewById(R.id.movie_tiles);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        nextPage = findViewById(R.id.nextPage);
        previousPage = findViewById(R.id.previousPage);
        pageCountElement = findViewById(R.id.pageCount);
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        page5 = findViewById(R.id.page5);
        page6 = findViewById(R.id.page6);

        page1.setText("1");

        s = findViewById(R.id.s);
        sorter = findViewById(R.id.sorter);
        String[] sorterOptions = new String[]{"By Popularity", "By Rating", "By Title", "By Newest Release", "On Video", "By Genre"};

        final ArrayAdapter<String> sorterAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sorterOptions);
        sorter.setAdapter(sorterAdapter);


        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount += 1;
                if (pageCount >= 96) {
                    pageCount = 96;
                    nextPage.setVisibility(View.GONE);
                }
                if (previousPage.getVisibility() == View.GONE) {
                    previousPage.setVisibility(View.VISIBLE);
                }
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        //  I cannot seem to set the visibility to GONE to start out with, because when pressing Next fast enough, as Previous Page is gone by default (page count is always set to 1 first), get Visibility == GONE
        //  is triggered just because it has not loaded the page count fast enough.
        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount -= 1;
                if (pageCount <= 1) {
                    pageCount = 1;
                    previousPage.setVisibility(View.GONE);
                }
                if (nextPage.getVisibility() == View.GONE) {
                    nextPage.setVisibility(View.VISIBLE);
                }
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 1;
                previousPage.setVisibility(View.GONE);
                if (nextPage.getVisibility() == View.GONE) {
                    nextPage.setVisibility(View.VISIBLE);
                }
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = 1;
                previousPage.setVisibility(View.GONE);
                if (nextPage.getVisibility() == View.GONE) {
                    nextPage.setVisibility(View.VISIBLE);
                }
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = Integer.parseInt(page2.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        page3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = Integer.parseInt(page3.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        page4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = Integer.parseInt(page4.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        page5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = Integer.parseInt(page5.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        page6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageCount = Integer.parseInt(page6.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pageCount", pageCount);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

    }

    public void makeURLAndJSON() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            pageCount = extras.getInt("pageCount");

            if (pageCount == 1 && previousPage.getVisibility() == View.VISIBLE) {
                previousPage.setVisibility(View.GONE);
            }
        }
        String sURL = "http://api.themoviedb.org/3/discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22&page=" + pageCount + "&api_key=64b6f3a69e5717b13ed8a56fe4417e71"; //just a string
        //String genreURL = "https://api.themoviedb.org/3/genre/movie/list?api_key=150abae665d285b41bfd2c0145215ed3&language=en-US";
        // Connect to the URL using java's native library
        URL url = null;
        URL gUrl = null;
        try {
            url = new URL(sURL);
            //gUrl = new URL(genreURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection request = null;
        URLConnection requestGenre = null;
        try {
            request = url.openConnection();
            //requestGenre = gUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
            //requestGenre.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        JsonElement mainResults = null;
        JsonElement maxPage = null;

        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
            mainResults = ((JsonObject)root).get("results");
            maxPage = ((JsonObject) root).get("total_pages");
            //rootGenre = (JsonArray) jp.parse(new InputStreamReader((InputStream) requestGenre.getContent())).getAsJsonObject().get("genres");

        } catch (IOException e) {
            e.printStackTrace();
        }




        rootArray = mainResults.getAsJsonArray();
        maxPages = maxPage.getAsInt();
        page6.setText(maxPages.toString());
        if (pageCount <= 3) {
            page2.setText("2");
            page3.setText("3");
            page4.setText("4");
            page5.setText("5");
        }
        if (pageCount >= (maxPages - 3)) {
            page2.setText("92");
            page3.setText("93");
            page4.setText("94");
            page5.setText("95");
        }
        else if (pageCount > 3 && pageCount < (maxPages - 3)) {
            Integer page2Int = pageCount - 1;
            Integer page4Int = pageCount + 1;
            Integer page5Int = pageCount + 2;
            page2.setText(page2Int.toString());
            page3.setText(pageCount.toString());
            page4.setText(page4Int.toString());
            page5.setText(page5Int.toString());
        }
        pageCountElement.setText(pageCount + "/" + maxPages);
        //genreArray = rootGenre.getAsJsonArray();
        // data = rootArray.toString();
        //rootobj = root.getAsJsonObject(); //May be an array, may be an object.
    }

    public JSONObject[] getMovieArray() {
        Gson gson = new Gson();
        JSONObject[] movies = new JSONObject[rootArray.size()];
        for (int i = 0; i < rootArray.size(); i++) {
            try {
                movies[i] = new JSONObject(gson.toJson(rootArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public String getGenres(Integer[] genresInteger) {
        String genres = "";
        Integer[] genreIDs = genresInteger;
        Integer genreCount = genreIDs.length;
        for (int i = 0; i < genreCount; i++) {
            if (genreIDs[i] == 28) {
                genres += "Action";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 12) {
                genres += "Adventure";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 16) {
                genres += "Animation";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 35) {
                genres += "Comedy";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 80) {
                genres += "Crime";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 99) {
                genres += "Documentary";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 18) {
                genres += "Drama";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 10751) {
                genres += "Family";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 14) {
                genres += "Fantasy";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 36) {
                genres += "History";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 27) {
                genres += "Horror";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 10402) {
                genres += "Music";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 9648) {
                genres += "Mystery";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 10749) {
                genres += "Romance";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 878) {
                genres += "Science Fiction";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 10770) {
                genres += "TV Movie";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 53) {
                genres += "Thriller";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 10752) {
                genres += "War";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
            if (genreIDs[i] == 37) {
                genres += "Western";
                if (i != genreCount - 1) {
                    genres += ", ";
                }
            }
        }
        return genres;
    }

    public void makeMovies() {
        movieArray = new ArrayList<movieObject>();
        movieTitles = new ArrayList<String>();
        movieDates = new ArrayList<String>();
        for (int i = 0; i < rootArray.size(); i++) {
            try {
                String title = getMovieArray()[i].get("title").toString();
                Integer voteCount = Integer.parseInt(getMovieArray()[i].get("vote_count").toString());
                Integer movieID = Integer.parseInt(getMovieArray()[i].get("id").toString());
                String isVideo = "False";
                if (getMovieArray()[i].get("video").toString().equals("true")) {
                    isVideo = "True";
                }
                Double voteAverage = Double.parseDouble(getMovieArray()[i].get("vote_average").toString());
                Double popularity = Double.parseDouble(getMovieArray()[i].get("popularity").toString());
                String moviePosterPath = "https://image.tmdb.org/t/p/w500" + getMovieArray()[i].get("poster_path").toString();
                String originalLanguage = getMovieArray()[i].get("original_language").toString().replaceAll("\"","");
                String originalTitle = getMovieArray()[i].get("original_title").toString().replaceAll("\"","");

                //  Below code gets the genre array.  I have tried a less costly way to get the array of integers, but parsing and casting isn't working so this works for now:

                String genreString = getMovieArray()[i].get("genre_ids").toString().replaceAll("\\]", "").replaceAll("\\[", "");
                String[] genreStringArray = genreString.split(",");
                Integer[] genreIntegerArray = new Integer[genreStringArray.length];
                if (!genreString.equals("")) {
                    for (int j = 0; j < genreStringArray.length; j++) {
                        genreIntegerArray[j] = Integer.parseInt(genreStringArray[j]);
                    }
                } else if (genreString.equals("")) {
                    genreIntegerArray = new Integer[0];
                }

                String movieBackdropPath = "https://image.tmdb.org/t/p/w500" + getMovieArray()[i].get("backdrop_path").toString().replaceAll("\"","");
                String adult = "False";
                if (getMovieArray()[i].get("adult").toString().replaceAll("\"","").equals("true")) {
                    adult = "True";
                }
                String overview = getMovieArray()[i].get("overview").toString().replaceAll("\"","");
                String releaseDateString = getMovieArray()[i].get("release_date").toString().replaceAll("\"","");;

                //  Using Arrays instead of ArrayList here might be slightly better for updatability, as the methods to retrieve values won't need to change in the movie object class
                String[] allStrings = new String[10];
                allStrings[0] = title;
                allStrings[1] = isVideo;
                allStrings[2] = moviePosterPath;
                allStrings[3] = originalLanguage;
                allStrings[4] = originalTitle;
                allStrings[5] = getGenres(genreIntegerArray);
                allStrings[6] = movieBackdropPath;
                allStrings[7] = adult;
                allStrings[8] = overview;
                allStrings[9] = releaseDateString;

                ArrayList<Integer> allIntegers = new ArrayList<>();
                allIntegers.add(voteCount);
                allIntegers.add(movieID);
                Double[] allDoubles = new Double[2];
                allDoubles[0] = voteAverage;
                allDoubles[1] = popularity;

                movieArray.add(new movieObject(allStrings, allIntegers, allDoubles));
                movieTitles.add(title);
                movieDates.add(releaseDateString);
                resultsSize += 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //  The following 6 methods could potentially be combined into 1 or 2 methods

    public void sortMovieTitles(ArrayList<String> movies) {
        Collections.sort(movies, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        movieObject[] temp = new movieObject[movieArray.size()];
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.size(); j++) {
                if (movies.get(i).equals(movieArray.get(j).getMovieTitle())) {
                    temp[i] = movieArray.get(j);
                }
            }
        }
        movieArray = new ArrayList<>(Arrays.asList(temp));
    }

    public void sortByDate(ArrayList<String> dates) {
        Collections.sort(dates, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        Collections.reverse(dates);
        movieObject[] temp = new movieObject[movieArray.size()];
        for (int i = 0; i < dates.size(); i++) {
            for (int j = 0; j < dates.size(); j++) {
                if (dates.get(i).equals(movieArray.get(j).getReleaseDateString()) && !Arrays.asList(temp).contains(movieArray.get(j))) {
                    temp[i] = movieArray.get(j);
                }
            }
        }
        movieArray = new ArrayList<>(Arrays.asList(temp));
    }


    //  This methods clumps the genres alphebetically together, however because many could have more than one genre it is not very useful sometimes.  To search for movies this way it's more helpful to know the
    //  'earliest genre in the alphabet'
    public void sortByGenres(ArrayList<movieObject> genres) {
        ArrayList<movieObject> temp = new ArrayList<movieObject>();
        String[] genreTypes = new String[]{"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "History", "Horror",
            "Music", "Mystery", "Romance", "Science Fiction", "TV Movie", "Thriller", "War", "Western"};

        for (int i = 0; i < genreTypes.length; i++) {
            for (int j = 0; j < genres.size(); j++) {
                if (genres.get(j).getGenres().contains(genreTypes[i]) && !temp.contains(genres.get(j))) {
                    temp.add(genres.get(j));
                }
            }
        }

        movieArray = temp;
    }

    public void sortByPopularity(ArrayList<movieObject> movies) {
        ArrayList<Double> popularityArray = new ArrayList<Double>();
        for (int i = 0; i < movies.size(); i++) {
            popularityArray.add(movies.get(i).getPopularity());
        }
        Collections.sort(popularityArray);
        Collections.reverse(popularityArray);
        movieObject[] temp = new movieObject[movieArray.size()];

        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.size(); j++) {
                if (popularityArray.get(i) == movieArray.get(j).getPopularity() && !Arrays.asList(temp).contains(movieArray.get(j))) {
                    temp[i] = movieArray.get(j);
                }
            }
        }
        movieArray = new ArrayList<>(Arrays.asList(temp));
    }

    public void sortByRating(ArrayList<movieObject> movies) {
        ArrayList<Double> ratingArray = new ArrayList<Double>();
        for (int i = 0; i < movies.size(); i++) {
            ratingArray.add(movies.get(i).getVoteAverage());
        }
        Collections.sort(ratingArray);
        Collections.reverse(ratingArray);
        movieObject[] temp = new movieObject[movieArray.size()];

        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.size(); j++) {
                if (ratingArray.get(i) == movieArray.get(j).getVoteAverage() && !Arrays.asList(temp).contains(movieArray.get(j))) {
                    temp[i] = movieArray.get(j);
                }
            }
        }
        movieArray = new ArrayList<>(Arrays.asList(temp));
    }

    public void byOnVideo(ArrayList<movieObject> onVideo) {
        ArrayList<movieObject> temp = new ArrayList<>();
        for (int i = 0; i < onVideo.size(); i++) {
            if (onVideo.get(i).getIsVideoString().equals("True")) {
                temp.add(onVideo.get(i));
            }
        }
        if (temp.size() == 0) {
            Toast toast= Toast.makeText(getApplicationContext(), "No results found for On Video", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 210);
            toast.show();
        }
        for (int i = 0; i < onVideo.size(); i++) {
            if (onVideo.get(i).getIsVideoString().equals("False")) {
                temp.add(onVideo.get(i));
            }
        }
        movieArray = temp;
    }


    //  Just in case I want to use the API Genre Endpoint to get a list, I can keep this method for now.

    public JSONObject[] getGenreArray() {
        Gson gson = new Gson();
        JSONObject[] genres = new JSONObject[genreArray.size()];
        for (int i = 0; i < genreArray.size(); i++) {
            try {
                genres[i] = new JSONObject(gson.toJson(genreArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return genres;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        new AsyncCaller().execute();

    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
        }
        @Override
        protected Void doInBackground(Void... params) {


            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            makeURLAndJSON();
            makeMovies();
            adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            final android.support.v7.widget.SearchView.SearchAutoComplete sAutoComplete = (android.support.v7.widget.SearchView.SearchAutoComplete)s.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            sAutoComplete.setBackgroundColor(getResources().getColor(R.color.lightGray));
            sAutoComplete.setTextColor(getResources().getColor(R.color.black));
            sAutoComplete.setDropDownBackgroundResource(R.color.white);
            ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, movieTitles);
            recyclerView.setAdapter(adapter);
            sAutoComplete.setAdapter(newsAdapter);

            sAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                    String queryString = (String)adapterView.getItemAtPosition(itemIndex);
                    sAutoComplete.setText("" + queryString);
                    for (int i = 0; i < movieArray.size(); i++) {
                        if (movieArray.get(i).getMovieTitle().equals(queryString)) {
                            hideKeyboard(MainActivity.this);
                            recyclerView.smoothScrollToPosition(i);
                        }
                    }
                }
            });

            sorter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        sortByPopularity(movieArray);
                        adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);
                        recyclerView.setAdapter(adapter);
                    }
                    if (position == 1) {
                        sortByRating(movieArray);
                        adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);
                        recyclerView.setAdapter(adapter);
                    }
                    if (position == 2) {
                        sortMovieTitles(movieTitles);
                        adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);
                        recyclerView.setAdapter(adapter);
                    }
                    if (position == 3) {
                        sortByDate(movieDates);
                        adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);
                        recyclerView.setAdapter(adapter);
                    }
                    if (position == 4) {
                        byOnVideo(movieArray);
                        adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);
                        recyclerView.setAdapter(adapter);
                    }
                    if (position == 5) {
                        sortByGenres(movieArray);
                        adapter = new CustomAdapter(movieArray, MainActivity.this, pixelDensity);
                        recyclerView.setAdapter(adapter);
                    }


                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    sorter.setSelection(0);
                }
            });

            pageCountElement.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    pageCountElement.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    return false;
                }
            });

            pdLoading.dismiss();
        }

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
