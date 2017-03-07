package marcos.movieapp.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import marcos.movieapp.R;
import marcos.movieapp.data.entities.ResMovie;

import static com.google.common.base.Preconditions.checkNotNull;

class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeViewHolder> {
    @NonNull
    private Context context;
    private List<ResMovie> resMovies;

    HomeListAdapter(@NonNull Context context) {
        this.context = checkNotNull(context);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
            .inflate(R.layout.item_home_movie, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        view.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        ResMovie resMovie = resMovies.get(position);
        holder.getTitleTxt().setText(
            String.format("%s (%s)", resMovie.getTitle(), resMovie.getTyar()));
    }

    @Override
    public int getItemCount() {
        return resMovies != null ? resMovies.size() : 0;
    }

    void updateHomeResMovies(List<ResMovie> resMovies) {
        this.resMovies = resMovies;
        notifyDataSetChanged();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;

        HomeViewHolder(View itemView) {
            super(itemView);
            titleTxt = (TextView) itemView.findViewById(R.id.movie_title_year_txt);
        }

        TextView getTitleTxt() {
            return titleTxt;
        }
    }
}
