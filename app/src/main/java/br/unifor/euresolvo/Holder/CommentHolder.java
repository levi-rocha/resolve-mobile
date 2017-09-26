package br.unifor.euresolvo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.unifor.euresolvo.R;

public class CommentHolder  extends RecyclerView.ViewHolder {

    public TextView txtComentario;
    public TextView txtUsuario;

    public CommentHolder(View itemView) {
        super(itemView);
        txtComentario = (TextView) itemView.findViewById(R.id.txtComentario);
        txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
    }
}
