package br.unifor.euresolvo.Parcer;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Bean.UsersBean;

/**
 * Created by SamuelSantiago on 16/10/2017.
 */

public class UsersJSONParcer {

    public static List<UsersBean> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<UsersBean> usersBeanList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                UsersBean usersBean = new UsersBean();

                usersBean.setId(jsonObject.getInt("id"));
                usersBean.setUsername(jsonObject.getString("username"));
                usersBean.setEmail(jsonObject.getString("email"));
                usersBean.setPassword(jsonObject.getString("password"));
                usersBean.setPostList(jsonObject.getJSONObject("posts"));

                if (jsonObject.getString("photo") != null){
                    usersBean.setPhoto(jsonObject.getString("photo"));
                }


                usersBeanList.add(usersBean);
            }

            return usersBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
