package marcos.movieapp.models.OMDBapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OMDBResponse {

    @SerializedName("MovieOnList")
    @Expose
    private List<MovieOnList> movieOnList = null;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;

    public List<MovieOnList> getMovieOnList() {
        return movieOnList;
    }

    public void setMovieOnList(List<MovieOnList> movieOnList) {
        this.movieOnList = movieOnList;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
