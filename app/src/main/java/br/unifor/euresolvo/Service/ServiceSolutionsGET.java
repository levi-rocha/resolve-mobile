package br.unifor.euresolvo.Service;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Adapter.SolutionAdapter;
import br.unifor.euresolvo.Bean.SolutionBean;
import br.unifor.euresolvo.Parcer.SolutionJSONParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mbyte on 28/11/2017.
 */

public class ServiceSolutionsGET {

    List<SolutionBean> solutionBeanList;
    private int tag;
    ArrayList<SolutionBean> solutionList = new ArrayList<>();
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

            solutionBeanList = SolutionJSONParser.parseDados(results);

            switch (tag) {
                case 3:
                    if (solutionBeanList != null) {
                        for (SolutionBean solution : solutionBeanList) {
                            solutionList.add(solution);
                        }
                        SolutionAdapter adapter = new SolutionAdapter(solutionList);

                        rv.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }

        }
    }
}
