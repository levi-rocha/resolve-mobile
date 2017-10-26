package br.unifor.euresolvo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.unifor.euresolvo.R;

public class ReportsHolder extends RecyclerView.ViewHolder {

    public TextView txtAuthor;
    public TextView txtDescription;

    public ReportsHolder(View itemView) {
        super(itemView);
        txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
    }
}
