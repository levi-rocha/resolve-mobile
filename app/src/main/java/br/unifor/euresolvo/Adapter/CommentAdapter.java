package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.DTO.CommentDTO;
import br.unifor.euresolvo.Holder.CommentHolder;
import br.unifor.euresolvo.R;

public class CommentAdapter  extends RecyclerView.Adapter<CommentHolder>{

    private final List<CommentDTO> comments;

    public CommentAdapter(List<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.txtContent.setText(String.format(Locale.getDefault(), "%s",
                comments.get(position).getContent()
        ));
        holder.txtAuthor.setText(String.format(Locale.getDefault(), "%s",
                comments.get(position).getAuthorUsername()
        ));
    }

    @Override
    public int getItemCount() {
        return comments != null ? comments.size() : 0;
    }
}
