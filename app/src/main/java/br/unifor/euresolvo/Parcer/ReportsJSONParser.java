package br.unifor.euresolvo.Parcer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.unifor.euresolvo.Bean.ReportsBean;

public class ReportsJSONParser {

    public static List<ReportsBean> parseDados(String content) {
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<ReportsBean> reportsBeanList = new ArrayList<>();

            for (int i = 0; i< jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                String author = jsonObject.getString("authorUsername");
                String description = jsonObject.getString("description");

                ReportsBean reportBean = new ReportsBean(author, description);
                reportsBeanList.add(reportBean);
            }

            return reportsBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
