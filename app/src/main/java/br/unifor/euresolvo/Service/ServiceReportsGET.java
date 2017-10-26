package br.unifor.euresolvo.Service;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Adapter.PostsAdapter;
import br.unifor.euresolvo.Adapter.ReportsAdapter;
import br.unifor.euresolvo.Bean.ReportsBean;
import br.unifor.euresolvo.Parcer.ReportsJSONParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceReportsGET {

    List<ReportsBean> reportsBeanList;
    private String stringResults = "";
    TextView textView;
    private ArrayList<String> strings;
    private int tag;
    ArrayList<ReportsBean>reportList = new ArrayList<>();
    RecyclerView rv;
    ProgressBar progressBar;

    public void toRecyclerView(String url, RecyclerView rv, ProgressBar progressBar){
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
        this.progressBar = progressBar;
        this.rv = rv;
        tag = 3;
    }

    public class OkHttpHandler extends AsyncTask<String,String,String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);

            reportsBeanList = ReportsJSONParser.parseDados(results);

            switch (tag) {
                case 3:
                    if (reportsBeanList != null) {
                        for (ReportsBean report : reportsBeanList) {
                            reportList.add(report);
                        }
                        ReportsAdapter adapter = new ReportsAdapter(reportList);

                        rv.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }

        }
    }
}
