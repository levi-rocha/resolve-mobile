package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.Bean.SolutionBean;
import br.unifor.euresolvo.Holder.SolutionHolder;
import br.unifor.euresolvo.R;

/**
 * Created by Mbyte on 28/11/2017.
 */

public class SolutionAdapter  extends RecyclerView.Adapter<SolutionHolder> {

    private final List<SolutionBean> mSolutions;

    public SolutionAdapter(ArrayList solutions) {
        mSolutions = solutions;
    }

    @Override
    public SolutionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.solution_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SolutionHolder holder, int position) {
        holder.txtSolucao.setText(String.format(Locale.getDefault(), "%s",
                mSolutions.get(position).getContent()
        ));
        holder.txtUsuario.setText(String.format(Locale.getDefault(), "%s",
                mSolutions.get(position).getAuthorUsername()
        ));
    }

    @Override
    public int getItemCount() {
        return mSolutions != null ? mSolutions.size() : 0;
    }

    public void updateList(SolutionBean solution) {
        insertItem(solution);
    }

    private void insertItem(SolutionBean solution) {
        mSolutions.add(solution);
        notifyItemInserted(getItemCount());
    }

}
