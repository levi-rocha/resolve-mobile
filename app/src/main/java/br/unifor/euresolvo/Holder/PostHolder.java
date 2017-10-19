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
        //Pegando as referencias dos textviews da view
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
        txtQtd = (TextView) itemView.findViewById(R.id.txtQtd);
        txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
    }

    @Override
    public void onClick(View v) {
        //Pegando os valores e passando para outra Activity atraves do Intent
        Intent intent = new Intent(context, DetailPost.class);
        intent.putExtra("titulo", txtTitle.getText());
        intent.putExtra("descricao", txtDescription.getText());
        intent.putExtra("qtd", txtQtd.getText());
        intent.putExtra("usuario", txtUsuario.getText());
        //Startando a Activity
        context.startActivity(intent);
    }

}
