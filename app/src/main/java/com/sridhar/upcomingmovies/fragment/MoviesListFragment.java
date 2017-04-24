package com.sridhar.upcomingmovies.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sridhar.upcomingmovies.R;
import com.sridhar.upcomingmovies.bean.Movie;
import com.sridhar.upcomingmovies.bean.MovieResult;
import com.sridhar.upcomingmovies.fragment.adapter.MoviesListAdapter;
import com.sridhar.upcomingmovies.utils.Constants;

import java.util.List;

/**
 * Created by SriMaddy on 4/24/2017.
 */
public class MoviesListFragment extends Fragment {

    // UI references
    private RecyclerView mRecyclerView;

    private MoviesListAdapter mAdapter;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        findViewsById(view);
        initRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sendMoviesListRequest();
    }

    private void findViewsById(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
    }

    private void sendMoviesListRequest() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
            String URL = Constants.MOVIE_LIST_URL + "?" + Constants.KEY_NAME_API_KEY + "=" + Constants.KEY_VALUE_API_KEY;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("response=>", response);
                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                    MovieResult movieResult = gson.fromJson(response, MovieResult.class);
                    List<Movie> movies = movieResult.getMovies();
                    Log.i("movies", movies.toString());

                    setAdapter(movies);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error=>", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                /*@Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put(Constants.KEY_NAME_API_KEY, Constants.KEY_VALUE_API_KEY);
                    return params;
                }*/
            };

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAdapter(List<Movie> movies) {
        mAdapter = new MoviesListAdapter(movies, mActivity);
        mRecyclerView.setAdapter(mAdapter);
    }
}
