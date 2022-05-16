package com.yan.paging.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yan.paging.Movie;
import com.yan.paging.R;

public class MoviePagedListAdapter extends PagedListAdapter<Movie,MoviePagedListAdapter.MovieViewHolder> {
    private Context context;

    // 只更新需要更新的元素
    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(Movie oldItem,Movie newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(Movie oldItem,Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MoviePagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MovieViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MoviePagedListAdapter.MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        Glide.with(context)
                .load(movie.img)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
        holder.textViewTitle.setText(movie.title);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.imageView);
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }
}
