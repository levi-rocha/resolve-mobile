package br.unifor.euresolvo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.unifor.euresolvo.R;

public class CommentHolder  extends RecyclerView.ViewHolder {

    public TextView txtContent;
    public TextView txtAuthor;

    public CommentHolder(View itemView) {
        super(itemView);
        txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
    }
}
