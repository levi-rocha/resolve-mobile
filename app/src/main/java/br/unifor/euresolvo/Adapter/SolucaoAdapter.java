package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.Bean.SolucaoBean;
import br.unifor.euresolvo.Holder.SolucaoHolder;
import br.unifor.euresolvo.R;


public class SolucaoAdapter extends RecyclerView.Adapter<SolucaoHolder> {

    private final List<SolucaoBean> mSolucoes;

    public SolucaoAdapter(ArrayList solucoes) {
        mSolucoes = solucoes;
    }

    @Override
    public SolucaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolucaoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.solucao_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SolucaoHolder holder, int position) {
        holder.txtSolucao.setText(String.format(Locale.getDefault(), "%s",
                mSolucoes.get(position).getContent()
        ));
        holder.txtUsuarioSolucao.setText(String.format(Locale.getDefault(), "%s",
                mSolucoes.get(position).getAuthorUsername()
        ));
    }

    @Override
    public int getItemCount() {
        return mSolucoes != null ? mSolucoes.size() : 0;
    }

    public void updateList(SolucaoBean solucao) {
        insertItem(solucao);
    }

    private void insertItem(SolucaoBean solucao) {
        mSolucoes.add(solucao);
        notifyItemInserted(getItemCount());
    }
}
