package br.unifor.euresolvo.Adapter;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.Bean.PostBean;
import br.unifor.euresolvo.Holder.PostHolder;
import br.unifor.euresolvo.R;

public class PostAdapter extends RecyclerView.Adapter<PostHolder>{
    private final List<PostBean> mPosts;

    public PostAdapter(ArrayList posts) {
        mPosts = posts;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        holder.txtTitle.setText(String.format(Locale.getDefault(), "%s",
                mPosts.get(position).getTitulo()
        ));
        holder.txtDescription.setText(String.format(Locale.getDefault(), "%s",
                mPosts.get(position).getDescricao()
        ));
        holder.txtQtd.setText(String.format(Locale.getDefault(), "%s",
                String.valueOf(mPosts.get(position).getQtdLikes())
        ));
        holder.txtUsuario.setText(String.format(Locale.getDefault(), "%s",
                mPosts.get(position).getUsuario()
        ));

        //holder.moreButton.setOnClickListener(view -> updateItem(position));
        //holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.size() : 0;
    }

    public void updateList(PostBean post) {
        insertItem(post);
    }

    private void insertItem(PostBean post) {
        mPosts.add(post);
        notifyItemInserted(getItemCount());
    }

    public void removeItem(int position) {
        mPosts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mPosts.size());
    }
}
