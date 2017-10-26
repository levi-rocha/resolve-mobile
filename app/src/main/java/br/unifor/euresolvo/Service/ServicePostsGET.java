package br.unifor.euresolvo.Service;


import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Adapter.PostsAdapter;
import br.unifor.euresolvo.Bean.PostsBean;
import br.unifor.euresolvo.Parcer.PostsJSONParcer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ServicePostsGET {

    List<PostsBean> postsBeanList;
    private String stringResults = "";
    TextView textView;
    private ArrayList<String> strings;
    private int tag;
    ArrayList<PostsBean>postList = new ArrayList<>();
    RecyclerView rv;
    ProgressBar progressBar;


    public void toTextView(String url, TextView textView){
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
        this.textView = textView;
        tag = 1;
    }

    public void toStringsArray(String url, ArrayList<String> strings){
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
        this.strings = strings;
        tag = 2;
    }

    public void toPostsArray(String url, ArrayList<PostsBean>postList){
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
        this.postList = postList;
        tag = 3;
    }

    public void toRecyclerView(String url, RecyclerView rv){
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
        this.rv = rv;
        tag = 3;
    }

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
        protected String doInBackground(String...params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);

            postsBeanList = PostsJSONParcer.parseDados(results);

            switch (tag){
                case 1:
                    if (postsBeanList != null){
                        for (PostsBean post: postsBeanList) {
                            stringResults += post.getTitle() + "\n";
                        }
                        textView.setText(stringResults);
                    }
                    break;
                case 2:
                    if (postsBeanList != null){
                        for (PostsBean post: postsBeanList) {
                            strings.add(post.getTitle() + "\n");
                        }
                    }
                    break;
                case 3:
                    if (postsBeanList != null){
                        for (PostsBean post: postsBeanList) {
                            postList.add(post);
                        }
                        PostsAdapter adapter = new PostsAdapter(postList);

                        rv.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


    }


}
