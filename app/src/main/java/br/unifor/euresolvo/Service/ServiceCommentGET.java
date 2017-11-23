package br.unifor.euresolvo.Service;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Adapter.CommentAdapter;
import br.unifor.euresolvo.Bean.CommentBean;
import br.unifor.euresolvo.Parcer.CommentJSONParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceCommentGET {

    List<CommentBean> commentBeanList;
    private int tag;
    ArrayList<CommentBean> commentList = new ArrayList<>();
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

            commentBeanList = CommentJSONParser.parseDados(results);

            switch (tag) {
                case 3:
                    if (commentBeanList != null) {
                        for (CommentBean report : commentBeanList) {
                            commentList.add(report);
                        }
                        CommentAdapter adapter = new CommentAdapter(commentList);

                        rv.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }

        }
    }
}
