package br.unifor.euresolvo.Parcer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Bean.SolutionBean;

/**
 * Created by Mbyte on 28/11/2017.
 */

public class SolutionJSONParser {

    public static List<SolutionBean> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<SolutionBean> solutionBeanList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                String author = jsonObject.getString("authorUsername");
                String description = jsonObject.getString("content");

                SolutionBean solutionBean = new SolutionBean(description, author);
                solutionBeanList.add(solutionBean);
            }

            return solutionBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
