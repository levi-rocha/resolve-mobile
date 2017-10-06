package br.unifor.euresolvo.Holder;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.unifor.euresolvo.DetailPost;
import br.unifor.euresolvo.R;

public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context context;
    public TextView txtTitle;
    public TextView txtDescription;
    public TextView txtQtd;
    public TextView txtUsuario;

    public PostHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
        txtQtd = (TextView) itemView.findViewById(R.id.txtQtd);
        txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailPost.class);
        intent.putExtra("titulo", txtTitle.getText());
        intent.putExtra("descricao", txtDescription.getText());
        intent.putExtra("qtd", txtQtd.getText());
        intent.putExtra("usuario", txtUsuario.getText());
        context.startActivity(intent);
    }

}
