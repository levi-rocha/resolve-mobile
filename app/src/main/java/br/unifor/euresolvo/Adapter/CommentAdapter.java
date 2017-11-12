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

    private final List<CommentDTO> mComments;

    public CommentAdapter(List<CommentDTO> comments) {
        mComments = comments;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.txtComentario.setText(String.format(Locale.getDefault(), "%s",
                mComments.get(position).getContent()
        ));
        holder.txtUsuario.setText(String.format(Locale.getDefault(), "%s",
                mComments.get(position).getAuthorUsername()
        ));
    }

    @Override
    public int getItemCount() {
        return mComments != null ? mComments.size() : 0;
    }

    public void updateList(CommentDTO comment) {
        insertItem(comment);
    }

    private void insertItem(CommentDTO comment) {
        mComments.add(comment);
        notifyItemInserted(getItemCount());
    }
}
