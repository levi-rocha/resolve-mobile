package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.unifor.euresolvo.DTO.PostSimpleDTO;
import br.unifor.euresolvo.R;

/**
 * Created by SamuelSantiago on 14/10/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>{

    //ArrayList<PostsBean> posts;
    public PostsAdapter(List<PostSimpleDTO> posts){
        this.posts = posts;
    }

    private List<PostSimpleDTO> posts;

    public static class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView contentView;
        TextView authorView;


        PostsViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.post_title);
            contentView = (TextView)itemView.findViewById(R.id.post_content_preview);
            authorView = (TextView)itemView.findViewById(R.id.authorUsername);

        }
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts, parent, false);
        PostsViewHolder pvh = new PostsViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(PostsViewHolder viewHolder, int i) {
        viewHolder.titleView.setText(posts.get(i).getTitle());
        viewHolder.contentView.setText(posts.get(i).getContentPreview());
        viewHolder.authorView.setText("- " + posts.get(i).getAuthorUsername());
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }
}