package com.sridhar.upcomingmovies.fragment.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.sridhar.upcomingmovies.R;
import com.sridhar.upcomingmovies.bean.Movie;
import com.sridhar.upcomingmovies.utils.DateUtils;

import java.util.List;

/**
 * Created by SriMaddy on 4/24/2017.
 */
public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private List<Movie> movies;
    private Activity mActivity;

    public MoviesListAdapter(List<Movie> movies, Activity activity) {
        this.movies = movies;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.movies_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.titleLbl.setText(movie.getTitle());
        holder.dateLbl.setText(DateUtils.getFormattedDate(movie.getReleaseDate()));

        if(movie.isAdult()) {
            holder.adultLbl.setText("(A)");
        } else {
            holder.adultLbl.setText("(U/A)");
        }

        Ion.with(mActivity).load(movie.getPosterImg()).withBitmap().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).intoImageView(holder.posterImg);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, movie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mainLayout;
        ImageView posterImg;
        TextView titleLbl;
        TextView dateLbl;
        TextView adultLbl;

        ViewHolder(View itemView) {
            super(itemView);
            mainLayout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            posterImg = (ImageView) itemView.findViewById(R.id.poster_img);
            titleLbl = (TextView) itemView.findViewById(R.id.name_lbl);
            dateLbl = (TextView) itemView.findViewById(R.id.date_lbl);
            adultLbl = (TextView) itemView.findViewById(R.id.adult_lbl);
        }
    }
}
