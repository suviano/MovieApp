package marcos.movieapp.models;

public class ShortMovie {
    private String title;
    private String year;
    private String poster;

    public ShortMovie(String title, String year, String poster) {
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
