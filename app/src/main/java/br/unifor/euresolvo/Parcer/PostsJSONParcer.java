package br.unifor.euresolvo.Parcer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Bean.PostBean;

/**
 * Created by SamuelSantiago on 13/10/2017.
 */

public class PostsJSONParcer {

    public static List<PostBean> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<PostBean> postBeanList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PostBean postBean = new PostBean();

                postBean.setId(jsonObject.getInt("id"));
                postBean.setTitle(jsonObject.getString("title"));
                postBean.setAuthorUsername(jsonObject.getString("authorUsername"));
                postBean.setVoteCount(jsonObject.getInt("voteCount"));
                postBean.setDate(jsonObject.getString("date"));
                postBean.setContentPreview(jsonObject.getString("contentPreview"));

                postBeanList.add(postBean);
            }

            return postBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
