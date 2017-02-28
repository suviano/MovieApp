package marcos.movieapp.searchMovie;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import marcos.movieapp.R;

class MovieOverviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = MovieOverviewHolder.class.getName();
    TextView movieYear;
    TextView movieTitle;
    ImageView moviePoster;

    MovieOverviewHolder(View itemView) {
        super(itemView);
        movieTitle = (TextView) itemView.findViewById(R.id.txt_movie_title);
        movieYear = (TextView) itemView.findViewById(R.id.txt_movie_year);
        moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "should get movie details");
    }
}
