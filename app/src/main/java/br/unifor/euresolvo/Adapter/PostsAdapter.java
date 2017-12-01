package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.unifor.euresolvo.DTO.PostSimpleDTO;
import br.unifor.euresolvo.R;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(PostSimpleDTO item);
    }

    public PostsAdapter(List<PostSimpleDTO> posts, OnItemClickListener listener){
        this.posts = posts;
        this.listener = listener;
    }

    private List<PostSimpleDTO> posts;
    private final OnItemClickListener listener;

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView contentView;
        TextView authorView;
        TextView votosView;

        PostsViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.post_title);
            contentView = (TextView)itemView.findViewById(R.id.post_content_preview);
            authorView = (TextView)itemView.findViewById(R.id.authorUsername);
            votosView = (TextView)itemView.findViewById(R.id.txtQtdVotos);
        }

        public void bind(final PostSimpleDTO item, final OnItemClickListener listener) {
            titleView.setText(item.getTitle());
            contentView.setText(item.getContentPreview());
            authorView.setText(item.getAuthorUsername());
            votosView.setText(item.getVoteCount() + " usuários têm este problema");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_posts, parent, false);
        PostsViewHolder pvh = new PostsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PostsViewHolder viewHolder, int position) {
        viewHolder.bind(posts.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}