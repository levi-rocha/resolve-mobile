package br.unifor.euresolvo.Service;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Callback extends JsonHttpResponseHandler {

    public void onSuccess(JSONArray result) {
        Log.d("callback", "onSuccess chamado mas não sobrescrito");
    };

    public void onFailure(String errorResponse) {
        Log.d("callback", "onFailure chamado mas não sobrescrito");
    };

    @Override
    public final void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
        onSuccess(timeline);
    }

    @Override
    public final void onSuccess(int statusCode, Header[] headers, JSONObject singleResult) {
        JSONArray timeline = new JSONArray();
        timeline.put(singleResult);
        onSuccess(timeline);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        JSONArray timeline = new JSONArray();
        timeline.put(responseString);
        onSuccess(timeline);
    }

    @Override
    public final void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        if (errorResponse == null)
            onFailure(null);
        try {
            onFailure(errorResponse.getJSONObject(0).getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        if (errorResponse == null)
            onFailure(null);
        try {
            onFailure(errorResponse.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        onFailure(responseString);
    }
}
