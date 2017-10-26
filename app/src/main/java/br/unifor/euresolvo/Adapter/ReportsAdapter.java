package br.unifor.euresolvo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifor.euresolvo.Bean.ReportsBean;
import br.unifor.euresolvo.Holder.ReportsHolder;
import br.unifor.euresolvo.R;


public class ReportsAdapter  extends RecyclerView.Adapter<ReportsHolder>{
    private final List<ReportsBean> mReports;

    public ReportsAdapter(ArrayList reports) {
        mReports = reports;
    }

    @Override
    public ReportsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReportsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ReportsHolder holder, int position) {
        holder.txtDescription.setText(String.format(Locale.getDefault(), "%s",
                mReports.get(position).getDescription()
        ));
        holder.txtAuthor.setText(String.format(Locale.getDefault(), "%s",
                mReports.get(position).getAuthor()
        ));
    }

    @Override
    public int getItemCount() {
        return mReports != null ? mReports.size() : 0;
    }

    public void updateList(ReportsBean report) {
        insertItem(report);
    }

    private void insertItem(ReportsBean report) {
        mReports.add(report);
        notifyItemInserted(getItemCount());
    }
}
