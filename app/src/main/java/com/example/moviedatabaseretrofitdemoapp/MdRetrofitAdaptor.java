package com.example.moviedatabaseretrofitdemoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedatabaseretrofitdemoapp.model.MovieDatabaseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MdRetrofitAdaptor extends RecyclerView.Adapter<MdRetrofitAdaptor.MdRetrofitViewHolder>{

    private MovieDatabaseModel mdRepos;
    private OnMdRetrofitClickedListener listener;

    public MdRetrofitAdaptor(MovieDatabaseModel mdRepos, OnMdRetrofitClickedListener listener) {
        this.mdRepos = mdRepos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MdRetrofitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout,parent,false);
        return new MdRetrofitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MdRetrofitViewHolder holder, int position) {

        holder.tvTitle.setText(mdRepos.getResults().get(position).getTitle());
        holder.tvOverview.setText(mdRepos.getResults().get(position).getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/w185"+mdRepos.getResults().get(position).getPosterPath()).into(holder.imgView);
        holder.ratingBar.setRating((mdRepos.getResults().get(position).getVoteAverage()).floatValue());

        holder.bind(mdRepos, listener);
    }

    @Override
    public int getItemCount() {
        return mdRepos.getResults().size();
    }


    public class MdRetrofitViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView imgView;
        public RatingBar ratingBar;

        public MdRetrofitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            imgView = itemView.findViewById(R.id.img_view);
            ratingBar = itemView.findViewById(R.id.rt_bar);
        }

        public void bind(final MovieDatabaseModel mdRepo, final OnMdRetrofitClickedListener listener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(mdRepo, getAdapterPosition());
                }
            });
        }

    }

    public interface OnMdRetrofitClickedListener {

        void onItemClicked(MovieDatabaseModel mdRepo, int position);

    }


}
