package br.unifor.euresolvo.Service;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import br.unifor.euresolvo.Bean.UsersBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by SamuelSantiago on 19/10/2017.
 */

public class ServiceLoginPOST {

    private String postBody;
    private String postUrl;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private UsersBean user;


    public ServiceLoginPOST(String url, String email, String pass){
        this.postUrl = url;
        this.postBody = "{\n" +
                "\"email\": \" " + email + " \",\n" +
                "\"password\": \" " + pass + " \"\n" +
                "}";
        user = new UsersBean();


        try {
            postRequest(postUrl,postBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UsersBean getUser() {
        return user;
    }

    void postRequest(String postUrl, String postBody) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, postBody);

        final Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG", response.body().string());
                if(response.body() != null){
                    Log.d("TAG", "não nulo");
                }else {
                    Log.d("TAG", "nulo");
                }

                try {
                    JSONObject userObject = new JSONObject(response.body().string());
                    user.setId(userObject.getInt("id"));
                    user.setEmail(userObject.getString("email"));
                    user.setUsername(userObject.getString("username"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
