package br.unifor.euresolvo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.unifor.euresolvo.R;

public class SolucaoHolder extends RecyclerView.ViewHolder {

    public TextView txtSolucao;
    public TextView txtUsuarioSolucao;

    public SolucaoHolder(View itemView) {
        super(itemView);
        txtSolucao = (TextView) itemView.findViewById(R.id.txtSolucao);
        txtUsuarioSolucao = (TextView) itemView.findViewById(R.id.txtUsuarioSolucao);
    }
}
