package br.unifor.euresolvo.Parcer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Bean.CommentBean;

public class CommentJSONParser {

    public static List<CommentBean> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<CommentBean> commentBeanList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                String author = jsonObject.getString("authorUsername");
                String description = jsonObject.getString("content");

                CommentBean commentBean = new CommentBean(description, author);
                commentBeanList.add(commentBean);
            }

            return commentBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
