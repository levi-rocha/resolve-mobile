package br.unifor.euresolvo.Service;

import android.os.AsyncTask;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Adapter.PostsAdapter;
import br.unifor.euresolvo.Bean.PostsBean;
import br.unifor.euresolvo.Bean.UsersBean;
import br.unifor.euresolvo.Parcer.PostsJSONParcer;
import br.unifor.euresolvo.Parcer.UsersJSONParcer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static br.unifor.euresolvo.R.id.textView;

public class ServiceUsersGET {

    List<UsersBean> usersBeanList;
    private String stringResults = "";
    private ArrayList<String> strings;
    private int tag;
    ArrayList<UsersBean> usersList = new ArrayList<>();


    public void toStringsArray(String url, ArrayList<String> strings){
        ServiceUsersGET.OkHttpHandler okHttpHandler= new ServiceUsersGET.OkHttpHandler();
        okHttpHandler.execute(url);
        this.strings = strings;
        tag = 2;
    }

    public void toPostsArray(String url, ArrayList<UsersBean>list){
        ServiceUsersGET.OkHttpHandler okHttpHandler= new ServiceUsersGET.OkHttpHandler();
        okHttpHandler.execute(url);
        this.usersList = list;
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

            usersBeanList = UsersJSONParcer.parseDados(results);

            switch (tag){
                case 2:
                    if (usersBeanList != null){
                        for (UsersBean user: usersBeanList) {
                            strings.add(user.getEmail() + "\n");
                        }
                    }
                    break;
                case 3:
                    if (usersBeanList != null){
                        for (UsersBean user: usersBeanList) {
                            usersList.add(user);
                        }

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
