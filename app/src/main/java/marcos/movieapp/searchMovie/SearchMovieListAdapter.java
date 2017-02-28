package marcos.movieapp.searchMovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.MovieOverview;

import static com.google.common.base.Preconditions.checkNotNull;

class SearchMovieListAdapter extends RecyclerView.Adapter<MovieOverviewHolder> {

    private final String TAG = SearchMovieListAdapter.class.getName();

    private Context context;
    private List<MovieOverview> movieOverviews;

    SearchMovieListAdapter(List<MovieOverview> movieOverviews, @NonNull Context context) {
        updateMoviesOverview(movieOverviews);
        setContext(context);
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
        return new MovieOverviewHolder(view);
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

}
