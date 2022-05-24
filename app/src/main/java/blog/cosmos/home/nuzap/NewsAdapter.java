package blog.cosmos.home.nuzap;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * {@link NewsAdapter} is an adapter for a recyclerview
 * that uses a list {@link java.util.ArrayList} of news {@link News} as its data source.
 * This adapter knows how to populate recyclerview with news data
 * in the MainAcitivity {@link blog.cosmos.home.nuzap.MainActivity} i.e Main UI of screen.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<News> allNews;

    /**
     * Constructs a new {@link NewsAdapter}.
     * @param allNews list news the adapter will use populate the screen
     */
    public NewsAdapter(List<News> allNews) {
        this.allNews = allNews;
    }

    /**
     *Constructs a new viewholder
     * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view,parent,false);
        return new ViewHolder(v);
    }


   /**
    * Binds view holder
    * **/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsTitle.setText(allNews.get(position).getTitle());

        // Extract featured image from image url using Picasso and
        // then putting that image into holders newsImage variable
        Picasso.get().load(allNews.get(position).getFeature_image()).into(holder.newsImage);


        // When a viewholder is clicked, user is redirected to details activity for
        // more detailed news data on the respect

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start new intent
                Intent i = new Intent(view.getContext(), blog.cosmos.home.nuzap.DetailsActivity.class);

                //Put details in the intent which DetailsAcitivty will extract.
                i.putExtra("title", allNews.get(position).getTitle());
                i.putExtra("description", allNews.get(position).getDescription());
                i.putExtra("content", allNews.get(position).getContent());
                i.putExtra("feature_image", allNews.get(position).getFeature_image());
                i.putExtra("news_url", allNews.get(position).getUrl());


                // Start the details activity
                view.getContext().startActivity(i);
            }
        });

    }

    // Returns total number of news available in allNews list variable
    @Override
    public int getItemCount() {
        return allNews.size();
    }


    /**
     * Helper method which clears the existing dataset of the recyclerview adapter.
     */
    public void clear(){
        if(allNews !=null && !allNews.isEmpty()) {
            int size = allNews.size();
            allNews.clear();

            // Notify the adapter that items were removed so adapter can update the recyclerview accordingly.
            notifyItemRangeRemoved(0, size);
        }

    }

    /**
     * Updates the adapter with new data
     * **/
    public void addAll(List<News> data){
        if (data != null && !data.isEmpty()) {
            // If new data is not empty then update allNews List
            allNews = data;
            //Notify the adapter for the change in dataset
            notifyDataSetChanged();
        }
    }


    /**
     * Class to hold metadata for a single news and show the same in
     * recyclerview {@link RecyclerView} via NewsAdapter {@link NewsAdapter}
     * **/
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImage;
        TextView newsTitle;

        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            newsImage = itemView.findViewById(R.id.news_image);
            newsTitle = itemView.findViewById(R.id.news_title);

        }
    }
}
