package br.unifor.euresolvo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.unifor.euresolvo.R;

/**
 * Created by Mbyte on 28/11/2017.
 */

public class SolutionHolder  extends RecyclerView.ViewHolder {

    public TextView txtSolucao;
    public TextView txtUsuario;

    public SolutionHolder(View itemView) {
        super(itemView);
        txtSolucao = (TextView) itemView.findViewById(R.id.txtSolucao);
        txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
    }
}
