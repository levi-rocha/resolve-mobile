package br.unifor.euresolvo.Service;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class Callback extends JsonHttpResponseHandler {

    public void onSuccess(JSONArray result) {
        Log.d("callback", "onSuccess chamado mas não sobrescrito");
    };

    public void onFailure(Throwable throwable, JSONArray errorResponse) {
        Log.d("callback", "onFailure chamado mas não sobrescrito");
    };

    @Override
    public final void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
        onSuccess(timeline);
    }

    @Override
    public final void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        onFailure(throwable, errorResponse);
    }


}
