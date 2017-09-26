package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.Bean.CommentBean;
import br.unifor.euresolvo.Holder.CommentHolder;
import br.unifor.euresolvo.R;

public class CommentAdapter  extends RecyclerView.Adapter<CommentHolder>{

    private final List<CommentBean> mComments;

    public CommentAdapter(ArrayList comments) {
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
                mComments.get(position).getComment()
        ));
        holder.txtUsuario.setText(String.format(Locale.getDefault(), "%s",
                mComments.get(position).getUsuario()
        ));
    }

    @Override
    public int getItemCount() {
        return mComments != null ? mComments.size() : 0;
    }

    public void updateList(CommentBean comment) {
        insertItem(comment);
    }

    private void insertItem(CommentBean comment) {
        mComments.add(comment);
        notifyItemInserted(getItemCount());
    }
}
