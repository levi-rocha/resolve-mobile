package br.unifor.euresolvo.Parcer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Bean.PostsBean;

/**
 * Created by SamuelSantiago on 13/10/2017.
 */

public class PostsJSONParcer {

    public static List<PostsBean> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<PostsBean> postsBeanList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PostsBean postsBean = new PostsBean();

                postsBean.setId(jsonObject.getInt("id"));
                postsBean.setTitle(jsonObject.getString("title"));
                postsBean.setAuthorUsername(jsonObject.getString("authorUsername"));
                postsBean.setVoteCount(jsonObject.getInt("voteCount"));
                postsBean.setDate(jsonObject.getString("date"));
                postsBean.setContentPreview(jsonObject.getString("contentPreview"));

                postsBeanList.add(postsBean);
            }

            return postsBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
