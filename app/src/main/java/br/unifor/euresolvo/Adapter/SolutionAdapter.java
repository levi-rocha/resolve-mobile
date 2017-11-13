package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.DTO.SolutionDTO;
import br.unifor.euresolvo.R;

public class SolutionAdapter extends RecyclerView.Adapter<SolutionAdapter.SolutionHolder> {

    private final List<SolutionDTO> solutions;

    public SolutionAdapter(List<SolutionDTO> solutions) {
        this.solutions = solutions;
    }

    @Override
    public SolutionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SolutionHolder holder, int position) {
        holder.txtContent.setText(String.format(Locale.getDefault(), "%s",
                solutions.get(position).getContent()
        ));
        holder.txtAuthor.setText(String.format(Locale.getDefault(), "%s",
                solutions.get(position).getAuthorUsername()
        ));
    }

    @Override
    public int getItemCount() {
        return solutions != null ? solutions.size() : 0;
    }

    public class SolutionHolder extends RecyclerView.ViewHolder {
        public TextView txtContent;
        public TextView txtAuthor;

        public SolutionHolder(View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
        }
    }

}
