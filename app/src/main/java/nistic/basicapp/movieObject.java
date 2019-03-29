package nistic.basicapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class movieObject {
    private String movieTitle;
    private Integer movieVoteCount;
    private Integer movieID;
    private Boolean isVideo;
    private String isVideoString;
    private Double voteAverage;
    private Double popularity;
    private String moviePosterPath;
    private Bitmap moviePoster;
    private String originalLanguage;
    private String originalTitle;
    private String genres;
    private Integer[] genreIDs;
    private Bitmap backdrop;
    private Boolean adult;
    private String isAdultString;
    private String overview;
    private Date releaseDate;

    private String[] allStrings;
    private ArrayList<Integer> allIntegers;
    private Double[] allDoubles;

    public movieObject(String firstLine, Double average, Integer[] genres, Bitmap poster, Double popularity) {
        movieTitle = firstLine;
        voteAverage = average;
        genreIDs = genres;
        moviePoster = poster;
        this.popularity = popularity;
    }

    public movieObject(String[] allStringsArray, ArrayList<Integer> allIntegersArray, Double[] allDoublesArray) {
        allStrings = allStringsArray;
        allIntegers = allIntegersArray;
        allDoubles = allDoublesArray;
    }

    public movieObject(String movieTitle, Integer movieVoteCount, Integer movieID, Boolean isVideo, Double voteAverage, Double popularity, Bitmap moviePoster, String originalLanguage, String originalTitle, Integer[] genreIDs, Bitmap backdropPath, Boolean adult, String overview, Date releaseDate) {
        this.movieTitle = movieTitle;
        this.movieVoteCount = movieVoteCount;
        this.movieID = movieID;
        this.isVideo = isVideo;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.moviePoster = moviePoster;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIDs = genreIDs;
        this.backdrop = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    //  Getters


    public String[] getAllStrings() {
        return allStrings;
    }

    public ArrayList<Integer> getAllIntegers() {
        return allIntegers;
    }

    public Double[] getAllDoubles() {
        return allDoubles;
    }

    public String getMovieTitle() {
        if (allStrings != null) {
            movieTitle = allStrings[0];
        }
        return movieTitle;
    }

    public Integer getMovieVoteCount() {
        if (allIntegers != null) {
            movieVoteCount = allIntegers.get(0);
        }
        return movieVoteCount;
    }

    public Integer getMovieID() {
        if (allIntegers != null) {
            movieID = allIntegers.get(1);
        }
        return movieID;
    }


    public String getVideo() {
        String video = "";
        if (isVideo) {
            video = "True";
        }
        else {
            video = "False";
        }
        return video;
    }

    public String getIsVideoString() {
        if (allStrings != null) {
            isVideoString = allStrings[1];
        }
        return isVideoString;
    }

    public String getIsAdultString() {
        return isAdultString;
    }

    public Double getVoteAverage() {
        if (allDoubles != null) {
            voteAverage = allDoubles[0];
        }
        return voteAverage;
    }

    public Double getPopularity() {
        if (allDoubles != null) {
            popularity = allDoubles[1];
        }
        return popularity;
    }

    public Bitmap getMoviePoster() {
        return moviePoster;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getGenres() {
        if (allStrings != null) {
            genres = allStrings[5];
        }
        return genres;
    }

    public Bitmap getBackdrop() {
        return backdrop;
    }

    public String getAdult() {
        String adultM = "";
        if (adult) {
            adultM = "Adult";
        }
        else {
            adultM = "";
        }
        return adultM;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDateString() {
        String releaseDateString = "";
        if (allStrings != null) {
            releaseDateString = allStrings[9];
        }
        return releaseDateString;
    }

    public String getMoviePosterPath() {
        if (allStrings != null) {
            moviePosterPath = allStrings[2];
        }
        return moviePosterPath;
    }



    //  Setters

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieVoteCount(Integer movieVoteCount) {
        this.movieVoteCount = movieVoteCount;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setMoviePoster(Bitmap moviePoster) {
        this.moviePoster = moviePoster;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setGenreIDs(Integer[] genreIDs) {
        this.genreIDs = genreIDs;
    }

    public void setBackdrop(Bitmap backdrop) {
        this.backdrop = backdrop;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer[] arrayFromString(String input) {
        List<Integer> list = new ArrayList<Integer>();
        for (String s : input.split(","))
        {
            list.add(Integer.parseInt(s));
        }
        return list.toArray(new Integer[list.size()]);
    }

    //  Potentially make class parceble to avoid 4 intent bundles, but it will probably be about the same speed

}
