package br.unifor.euresolvo.Service;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class Callback extends JsonHttpResponseHandler {

    public void success(JSONArray result) {};

    @Override
    public final void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
        success(timeline);
    }
}
