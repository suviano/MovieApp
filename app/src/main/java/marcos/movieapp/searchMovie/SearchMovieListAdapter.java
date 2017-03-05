package marcos.movieapp.searchMovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;

import static com.google.common.base.Preconditions.checkNotNull;

class SearchMovieListAdapter extends
    RecyclerView.Adapter<SearchMovieListAdapter.MovieOverviewHolder> implements View.OnClickListener {

    @NonNull
    private SearchResultClickListener searchResultClickListener;

    private Context context;
    private List<MovieOverview> movieOverviews;

    SearchMovieListAdapter(List<MovieOverview> movieOverviews, @NonNull Context context,
                           @NonNull SearchResultClickListener searchResultClickListener) {
        updateMoviesOverview(movieOverviews);
        setContext(context);
        this.searchResultClickListener = searchResultClickListener;
    }

    private void setContext(Context context) {
        this.context = checkNotNull(context);
    }

    void updateMoviesOverview(List<MovieOverview> movieOverviews) {
        this.movieOverviews = movieOverviews;
        notifyDataSetChanged();
    }

    @Override
    public MovieOverviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
            .inflate(R.layout.movie_overview_item, parent, false);
        MovieOverviewHolder movieOverviewHolder = new MovieOverviewHolder(view);
        view.setTag(movieOverviewHolder);
        view.setOnClickListener(this);
        return movieOverviewHolder;
    }

    @Override
    public void onBindViewHolder(MovieOverviewHolder holder, int position) {
        MovieOverview movieOverview = movieOverviews.get(position);
        Picasso.with(context).load(movieOverview.getPoster())
            .resize(200, 300).centerCrop()
            .into(holder.moviePoster);
        holder.movieTitle.setText(movieOverview.getTitle());
        holder.movieYear.setText(movieOverview.getYear());
    }

    @Override
    public int getItemCount() {
        return movieOverviews != null ? movieOverviews.size() : 0;
    }

    @Override
    public void onClick(View v) {
        MovieOverviewHolder movieOverviewHolder = (MovieOverviewHolder) v.getTag();
        searchResultClickListener.seeDetails(
            v, movieOverviews.get(movieOverviewHolder.getAdapterPosition()));
    }

    static class MovieOverviewHolder extends RecyclerView.ViewHolder {
        TextView movieYear;
        TextView movieTitle;
        ImageView moviePoster;

        MovieOverviewHolder(View itemView) {
            super(itemView);
            movieTitle = (TextView) itemView.findViewById(R.id.txt_movie_title);
            movieYear = (TextView) itemView.findViewById(R.id.txt_movie_year);
            moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
        }
    }

}
